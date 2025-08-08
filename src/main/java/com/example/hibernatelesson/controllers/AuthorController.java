package com.example.hibernatelesson.controllers;

import com.example.hibernatelesson.models.Author;
import com.example.hibernatelesson.models.DbBeanType;
import com.example.hibernatelesson.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final List<AuthorRepository> authorRepositories;
    private final Map<String, AuthorRepository> authorRepositoryMap;

    @GetMapping("/authors")
    public List<? extends Author> getAllAuthorsWithBooks(@RequestParam String dbType) {
//        AuthorRepository authorRepository = authorRepositories.stream()
//                .filter(repo -> repo.getDbType().name().equals(dbType))
//                .findFirst()
//                .orElseThrow(()-> new IllegalArgumentException("No type with name " + dbType));


//        String beanName = DbBeanType.getBeanNameFromDbBeanType(dbType);
//        AuthorRepository authorRepository = authorRepositoryMap.get(beanName);

        AuthorRepository authorRepository = switch (dbType) {
            case "JPA" -> authorRepositoryMap.get("jpaAuthorRepository");
            case "HIBERNATE" -> authorRepositoryMap.get("authorEntityManagerRepository");
            case "JDBC" -> authorRepositoryMap.get("authorJdbcRepository");
            default -> throw new IllegalStateException("Unexpected value: " + dbType);
        };

        return authorRepository.findAllWithBooks();
    }
}
