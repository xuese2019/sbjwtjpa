package com.ld.sbjwtjpa.sys.jpa;

import org.hibernate.dialect.MySQL5Dialect;

/**
 * @author ld
 * @name 在JPA建表时设置表的编码和排序规则
 * @table
 * @remarks
 */
public class MySQL5InnoDBDialectUtf8mb4 extends MySQL5Dialect {
    @Override
    public String getTableTypeString() {
        return "ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE utf8mb4_unicode_ci";
    }
}
