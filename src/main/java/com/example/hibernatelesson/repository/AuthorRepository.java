package com.example.hibernatelesson.repository;

import com.example.hibernatelesson.models.Author;
import com.example.hibernatelesson.models.DbBeanType;

import java.util.List;

public interface AuthorRepository {

    List<? extends Author> findAllWithBooks();

    DbBeanType getDbType();
}
