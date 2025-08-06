package com.example.hibernatelesson.models.jdbc;

import com.example.hibernatelesson.models.Author;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
public class AuthorJdbc extends Author {
    private Long id;
    private String name;
    private String email;
    private List<BookJdbc> books;
}
