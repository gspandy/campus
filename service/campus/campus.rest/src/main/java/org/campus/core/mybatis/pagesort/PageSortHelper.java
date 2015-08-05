package org.campus.core.mybatis.pagesort;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.binding.MapperMethod.ParamMap;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.campus.core.mybatis.pagesort.dialect.Dialect;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

public class PageSortHelper {

    private static Log logger = LogFactory.getLog(PageSortHelper.class);

    private static final Pattern ORDER_BY = Pattern.compile(".*order\\s+by\\s+.*", Pattern.CASE_INSENSITIVE);

    @SuppressWarnings("unchecked")
    public static <T> T findObjectFromParams(Object params, Class<T> objClass) {
        if (params == null) {
            return null;
        }

        if (objClass.isAssignableFrom(params.getClass())) {
            return (T) params;
        }

        if (params instanceof ParamMap) {
            ParamMap<Object> paramMap = (ParamMap<Object>) params;
            for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
                Object paramValue = entry.getValue();

                if (paramValue != null && objClass.isAssignableFrom(paramValue.getClass())) {
                    return (T) paramValue;
                }
            }
        }

        return null;
    }

    public static int queryTotal(String sql, MappedStatement mappedStatement, BoundSql boundSql, Dialect dialect)
            throws SQLException {

        Connection connection = null;
        PreparedStatement countStmt = null;
        ResultSet rs = null;
        try {

            connection = mappedStatement.getConfiguration().getEnvironment().getDataSource().getConnection();

            String countSql = dialect.getCountSql(sql);

            countStmt = connection.prepareStatement(countSql);
            BoundSql countBoundSql = copyFromBoundSql(mappedStatement, boundSql, countSql);
            setParameters(countStmt, mappedStatement, countBoundSql, boundSql.getParameterObject());

            rs = countStmt.executeQuery();
            int totalCount = 0;
            if (rs.next()) {
                totalCount = rs.getInt(1);
            }

            return totalCount;
        } catch (SQLException e) {
            logger.error("查询总记录数出错", e);
            throw e;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    logger.error("exception happens when doing: ResultSet.close()", e);
                }
            }

            if (countStmt != null) {
                try {
                    countStmt.close();
                } catch (SQLException e) {
                    logger.error("exception happens when doing: PreparedStatement.close()", e);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error("exception happens when doing: Connection.close()", e);
                }
            }
        }

    }

    public static MappedStatement copyFromNewSql(MappedStatement ms, BoundSql boundSql, String sql) {
        BoundSql newBoundSql = copyFromBoundSql(ms, boundSql, sql);

        return copyFromMappedStatement(ms, new BoundSqlSqlSource(newBoundSql));
    }

    public static Dialect getDialect(String dbtype) throws Exception {
        Resource resource = new ClassPathResource("/META-INF/mybatis/dialect.properties");
        Properties dialects = PropertiesLoaderUtils.loadProperties(resource);
        String dialectClassName = dialects.getProperty(dbtype);

        @SuppressWarnings("unchecked")
        Class<? extends Dialect> dialectClass = (Class<? extends Dialect>) Class.forName(dialectClassName);

        return BeanUtils.instantiate(dialectClass);
    }

    public static String applySorting(String sql, Sort sort) {
        if (null == sort || !sort.iterator().hasNext()) {
            return sql;
        }

        StringBuilder builder = new StringBuilder(sql);

        if (!ORDER_BY.matcher(sql).matches()) {
            builder.append(" order by ");
        } else {
            builder.append(", ");
        }

        for (Order order : sort) {
            builder.append(getOrderClause(order)).append(", ");
        }

        builder.delete(builder.length() - 2, builder.length());

        return builder.toString();
    }

    public static String removeSqlSemicolon(String sql) {
        final StringBuilder sqlBuilder = new StringBuilder(sql);
        if (sqlBuilder.lastIndexOf(";") == sqlBuilder.length() - 1) {
            sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);
        }

        return sqlBuilder.toString();
    }

    private static String getOrderClause(Order order) {
        String property = order.getProperty();
        String wrapped = order.isIgnoreCase() ? String.format("lower(%s)", property) : property;

        return String.format("%s %s", wrapped, toSqlDirection(order));
    }

    private static String toSqlDirection(Order order) {
        return order.getDirection().name().toLowerCase(Locale.US);
    }

    private static void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql,
            Object parameterObject) throws SQLException {
        ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
        parameterHandler.setParameters(ps);
    }

    private static class BoundSqlSqlSource implements SqlSource {
        BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }

    private static BoundSql copyFromBoundSql(MappedStatement ms, BoundSql boundSql, String sql) {
        BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), sql, boundSql.getParameterMappings(),
                boundSql.getParameterObject());
        for (ParameterMapping mapping : boundSql.getParameterMappings()) {
            String prop = mapping.getProperty();
            if (boundSql.hasAdditionalParameter(prop)) {
                newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
            }
        }
        return newBoundSql;
    }

    private static MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
        Builder builder = new Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());

        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null && ms.getKeyProperties().length != 0) {
            StringBuffer keyProperties = new StringBuffer();
            for (String keyProperty : ms.getKeyProperties()) {
                keyProperties.append(keyProperty).append(",");
            }
            keyProperties.delete(keyProperties.length() - 1, keyProperties.length());
            builder.keyProperty(keyProperties.toString());
        }

        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.resultSetType(ms.getResultSetType());
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());

        return builder.build();
    }
}
