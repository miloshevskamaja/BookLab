package mk.ukim.finki.books_lab.dto;

import mk.ukim.finki.books_lab.model.domain.Book;
import mk.ukim.finki.books_lab.model.domain.BookCopy;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayBookCopyDto(
        Long id,
        Book book
) {
    public static DisplayBookCopyDto from(BookCopy bookCopy){
        return new DisplayBookCopyDto(bookCopy.getId(), bookCopy.getBook());
    }
    public static List<DisplayBookCopyDto> from(List<BookCopy> bookCopyList){
        return bookCopyList.stream().map(DisplayBookCopyDto::from).collect(Collectors.toList());
    }
}
