package org.campus.core.mybatis.pagesort.dialect;

public interface Dialect {

    String getPageSql(String sql, int offset, int limit);

    String getCountSql(String sql);
}
