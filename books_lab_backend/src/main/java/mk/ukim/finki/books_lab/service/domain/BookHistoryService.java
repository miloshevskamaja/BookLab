package mk.ukim.finki.books_lab.service.domain;

import mk.ukim.finki.books_lab.model.domain.Book;
import mk.ukim.finki.books_lab.model.domain.BookHistory;

import java.util.List;
import java.util.Optional;

public interface BookHistoryService {
    List<BookHistory> findAll();
    Optional<BookHistory> save(Book book);
}
