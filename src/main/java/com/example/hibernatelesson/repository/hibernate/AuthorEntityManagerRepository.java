package com.example.hibernatelesson.repository.hibernate;

import com.example.hibernatelesson.models.hibernate.AuthorEntity;
import com.example.hibernatelesson.repository.AuthorRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Profile(value = "hibernate")
public class AuthorEntityManagerRepository implements AuthorRepository {

    private final EntityManager entityManager;

    public List<AuthorEntity> findAllWithBooks() {
        String jpql = """
                SELECT DISTINCT a FROM AuthorEntity a
                LEFT JOIN FETCH a.books
                ORDER BY a.id
                """;
        return entityManager
                .createQuery(jpql, AuthorEntity.class)
                .getResultList();
    }
}