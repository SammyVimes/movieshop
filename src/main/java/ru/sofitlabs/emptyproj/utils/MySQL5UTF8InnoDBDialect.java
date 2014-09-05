package ru.sofitlabs.emptyproj.utils;

import org.hibernate.dialect.MySQL5InnoDBDialect;

public class MySQL5UTF8InnoDBDialect extends MySQL5InnoDBDialect {
    @Override
    public String getTableTypeString() {
        return super.getTableTypeString() + " DEFAULT CHARSET=utf8";
    }
}
