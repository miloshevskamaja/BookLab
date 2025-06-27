package mk.ukim.finki.books_lab.dto;

import mk.ukim.finki.books_lab.model.domain.BookHistory;
import mk.ukim.finki.books_lab.model.enumerations.Category;

public record DisplayBookHistoryDto(
        Long id,
        String name,
        Category category,
        Long author
) {
    public static DisplayBookHistoryDto from(BookHistory bookHistory){
        return new DisplayBookHistoryDto(
                bookHistory.getId(),
                bookHistory.getName(),
                bookHistory.getCategory(),
                bookHistory.getAuthor().getId()
        );
    }
}
