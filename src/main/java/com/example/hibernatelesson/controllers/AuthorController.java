package com.example.hibernatelesson.controllers;

import com.example.hibernatelesson.models.Author;
import com.example.hibernatelesson.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorRepository authorRepository;

    @GetMapping("/authors")
    public List<? extends Author> getAllAuthorsWithBooks() {
        return authorRepository.findAllWithBooks();
    }
}
