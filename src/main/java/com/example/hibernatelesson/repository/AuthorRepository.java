package com.example.hibernatelesson.repository;

import com.example.hibernatelesson.models.Author;

import java.util.List;

public interface AuthorRepository {

    List<? extends Author> findAllWithBooks();
}
