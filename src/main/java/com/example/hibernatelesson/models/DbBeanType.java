package com.example.hibernatelesson.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum DbBeanType {
    JDBC("authorJdbcRepository"),
    HIBERNATE("authorEntityManagerRepository"),
    JPA("jpaAuthorRepository");

    private final String beanName;

    public static String getBeanNameFromDbBeanType(String dbType) {
        DbBeanType type = Arrays.stream(DbBeanType.values())
                .filter(e -> e.name().equals(dbType))
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("No type with name " + dbType));
        return type.beanName;
    }
}
