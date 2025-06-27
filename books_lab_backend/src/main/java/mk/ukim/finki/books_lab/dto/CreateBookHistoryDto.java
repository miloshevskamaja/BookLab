package mk.ukim.finki.books_lab.dto;

import mk.ukim.finki.books_lab.model.domain.Author;
import mk.ukim.finki.books_lab.model.domain.Book;
import mk.ukim.finki.books_lab.model.enumerations.Category;

public record CreateBookHistoryDto(String name,
                                   Category category,
                                   Long author) {
    public Book toBook(Category category, Author author) {
        return new Book(name, category, author);
    }
}
