package mk.ukim.finki.books_lab.service.domain;

import mk.ukim.finki.books_lab.model.domain.Book;


import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    Optional<Book> findBtId(Long id);
    Optional<Book> update(Long id, Book book);
    Optional<Book> save(Book book);
    Optional<Book> deleteById(Long id);
    Optional<Book> rentBook(Long id, Book book);

    Optional<Book> filterByNameAndAuthor(Book book);
    List<Book> getRelatedBooks(Long bookId);
    void soft_delete(Long id);
}
