package com.example.hibernatelesson.models.jdbc;

import com.example.hibernatelesson.models.Book;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;

@EqualsAndHashCode(callSuper = false)
@Data
public class BookJdbc extends Book {
    private Long id;
    private String title;
    private Date publishedDate;
    @JsonIgnore
    private Long authorId;
}
