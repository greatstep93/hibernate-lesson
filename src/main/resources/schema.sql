CREATE TABLE IF NOT EXISTS authors (
                                       id SERIAL PRIMARY KEY,
                                       name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL
    );

CREATE TABLE IF NOT EXISTS books (
                                     id SERIAL PRIMARY KEY,
                                     title VARCHAR(255) NOT NULL,
    published_date DATE,
    author_id INTEGER NOT NULL,
    FOREIGN KEY (author_id) REFERENCES authors(id)
    );

INSERT INTO authors (name, email) VALUES
                                      ('J.K. Rowling', 'jkrowling@example.com'),
                                      ('George Orwell', 'gorwell@example.com'),
                                      ('Agatha Christie', 'achristie@example.com');

INSERT INTO books (title, published_date, author_id) VALUES
                                                         ('Harry Potter and the Philosopher''s Stone', '1997-06-26', 1),
                                                         ('1984', '1949-06-08', 2),
                                                         ('Murder on the Orient Express', '1934-01-01', 3),
                                                         ('Harry Potter and the Chamber of Secrets', '1998-07-02', 1),
                                                         ('Animal Farm', '1945-08-17', 2);