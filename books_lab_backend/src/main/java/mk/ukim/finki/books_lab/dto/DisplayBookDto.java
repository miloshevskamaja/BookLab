package mk.ukim.finki.books_lab.dto;

import mk.ukim.finki.books_lab.model.domain.Book;
import mk.ukim.finki.books_lab.model.enumerations.Category;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayBookDto(
        Long id,
        String name,
        Category category,
        Long author,
        Boolean is_deleted
        //List<BookCopyDto> bookCopyList
) {
    public static DisplayBookDto from(Book book){
        return new DisplayBookDto(
                book.getId(),
                book.getName(),
                book.getCategory(),
                book.getAuthor(),
                book.getIs_deleted()
               // book.getBookCopy().
        );
    }
    public static List<DisplayBookDto> from(List<Book> books){
        return books.stream().map(DisplayBookDto::from).collect(Collectors.toList());
    }
}
