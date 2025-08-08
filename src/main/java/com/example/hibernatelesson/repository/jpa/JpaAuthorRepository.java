package com.example.hibernatelesson.repository.jpa;

import com.example.hibernatelesson.models.DbBeanType;
import com.example.hibernatelesson.models.hibernate.AuthorEntity;
import com.example.hibernatelesson.repository.AuthorRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaAuthorRepository extends JpaRepository<AuthorEntity, Integer>, AuthorRepository {

    @Query("SELECT DISTINCT a FROM AuthorEntity a LEFT JOIN FETCH a.books ORDER BY a.id")
    List<AuthorEntity> findAllWithBooks();

    @Override
    default DbBeanType getDbType() {
        return DbBeanType.JPA;
    }
}