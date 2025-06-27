package mk.ukim.finki.books_lab.dto;

import mk.ukim.finki.books_lab.model.domain.Book;
import mk.ukim.finki.books_lab.model.domain.BookCopy;

import java.util.List;
import java.util.stream.Collectors;

public record BookCopyDto(
        Long id,
        Book book
) {
    public static BookCopyDto from(BookCopy bookCopy){
        return new BookCopyDto(bookCopy.getId(),bookCopy.getBook());
    }
    public static List<BookCopyDto> from(List<BookCopy> bookCopies){
        return bookCopies.stream().map(BookCopyDto::from).collect(Collectors.toList());
    }
}
