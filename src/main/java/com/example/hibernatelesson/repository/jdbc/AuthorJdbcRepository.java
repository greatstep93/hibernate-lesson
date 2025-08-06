package com.example.hibernatelesson.repository.jdbc;

import com.example.hibernatelesson.models.Author;
import com.example.hibernatelesson.models.jdbc.AuthorJdbc;
import com.example.hibernatelesson.models.jdbc.BookJdbc;
import com.example.hibernatelesson.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Repository
@RequiredArgsConstructor
@Profile(value = "jdbc")
public class AuthorJdbcRepository implements AuthorRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<? extends Author> findAllWithBooks() {
        String sql = """
                SELECT a.id AS author_id, a.name AS author_name, a.email,
                       b.id AS book_id, b.title, b.published_date
                FROM authors a
                LEFT JOIN books b ON a.id = b.author_id
                ORDER BY a.id
                """;

        List<AuthorJdbc> rawResults = jdbcTemplate.query(sql, (rs, rowNum) -> {
            AuthorJdbc author = new AuthorJdbc();
            author.setId(rs.getLong("author_id"));
            author.setName(rs.getString("author_name"));
            author.setEmail(rs.getString("email"));

            BookJdbc book = null;
            Long bookId = rs.getLong("book_id");
            if (!rs.wasNull()) {
                book = new BookJdbc();
                book.setId(bookId);
                book.setTitle(rs.getString("title"));
                book.setPublishedDate(rs.getDate("published_date"));
                book.setAuthorId(author.getId());
            }

            author.setBooks(book != null ? List.of(book) : List.of());
            return author;
        });
        Map<Long, AuthorJdbc> merged = new LinkedHashMap<>();
        rawResults.forEach(author -> addAuthorToMap(author, merged));

        return new ArrayList<>(merged.values());
    }

    private void addAuthorToMap(AuthorJdbc author, Map<Long, AuthorJdbc> merged) {
        merged.computeIfAbsent(author.getId(), computeAuthor(author))
                .getBooks()
                .addAll(author.getBooks());
    }

    private Function<Long, AuthorJdbc> computeAuthor(AuthorJdbc author) {
        return id -> {
            AuthorJdbc newAuthor = new AuthorJdbc();
            newAuthor.setId(id);
            newAuthor.setName(author.getName());
            newAuthor.setEmail(author.getEmail());
            newAuthor.setBooks(new ArrayList<>());
            return newAuthor;
        };
    }

}