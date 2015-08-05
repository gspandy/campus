package org.campus.core.mybatis.pagesort;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.campus.core.mybatis.pagesort.dialect.Dialect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Intercepts({ @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
        RowBounds.class, ResultHandler.class }) })
public class PageSortInterceptor implements Interceptor {

    private static Log logger = LogFactory.getLog(PageSortInterceptor.class);
    private static int MAPPED_STATEMENT_INDEX = 0;
    private static int PARAMETER_INDEX = 1;
    private static int ROWBOUNDS_INDEX = 2;
    private Dialect dialect;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        final Object[] queryArgs = invocation.getArgs();
        final Object parameter = queryArgs[PARAMETER_INDEX];

        Pageable pageable = PageSortHelper.findObjectFromParams(parameter, Pageable.class);
        Sort sort = PageSortHelper.findObjectFromParams(parameter, Sort.class);

        if (pageable == null && sort == null) {
            return invocation.proceed();
        }

        final MappedStatement ms = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];
        final BoundSql boundSql = ms.getBoundSql(parameter);
        String sql = PageSortHelper.removeSqlSemicolon(boundSql.getSql().trim());

        if (pageable == null && sort != null) {
            String orderSql = PageSortHelper.applySorting(sql, sort);
            queryArgs[MAPPED_STATEMENT_INDEX] = PageSortHelper.copyFromNewSql(ms, boundSql, orderSql);

            return invocation.proceed();
        }

        final int total = PageSortHelper.queryTotal(sql, ms, boundSql, dialect);

        if (sort == null && pageable.getSort() != null) {
            sort = pageable.getSort();
        }

        if (sort != null) {
            sql = PageSortHelper.applySorting(sql, sort);
        }

        String pageSql = dialect.getPageSql(sql, pageable.getOffset(), pageable.getPageSize());

        queryArgs[ROWBOUNDS_INDEX] = new RowBounds(RowBounds.NO_ROW_OFFSET, RowBounds.NO_ROW_LIMIT);
        queryArgs[MAPPED_STATEMENT_INDEX] = PageSortHelper.copyFromNewSql(ms, boundSql, pageSql);

        Object ret = invocation.proceed();
        @SuppressWarnings("unchecked")
        Page<?> pi = new PageImpl<Object>((List<Object>) ret, pageable, total);
        List<Page<?>> result = new ArrayList<Page<?>>(1);
        result.add(pi);

        return result;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        String dbtype = properties.getProperty("dbtype");
        try {
            dialect = PageSortHelper.getDialect(dbtype);
        } catch (Exception e) {
            logger.error("设置dialect发生错误", e);
        }
    }

}
