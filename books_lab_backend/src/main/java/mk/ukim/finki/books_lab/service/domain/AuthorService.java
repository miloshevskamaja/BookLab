package mk.ukim.finki.books_lab.service.domain;


import mk.ukim.finki.books_lab.model.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Optional<Author> findById(Long Id);
    List<Author> findAll();
    void deleteById(long id);
    Optional<Author> save(Author author);
    Optional<Author> update(long id,Author author);
}
