package mk.ukim.finki.books_lab.service.application.impl;

import mk.ukim.finki.books_lab.dto.CreateBookHistoryDto;
import mk.ukim.finki.books_lab.dto.DisplayBookHistoryDto;
import mk.ukim.finki.books_lab.model.domain.Author;
import mk.ukim.finki.books_lab.model.enumerations.Category;
import mk.ukim.finki.books_lab.service.application.BookHistoryApplicationService;
import mk.ukim.finki.books_lab.service.domain.AuthorService;
import mk.ukim.finki.books_lab.service.domain.BookHistoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookHistoryApplicationServiceImpl implements BookHistoryApplicationService {
    private final BookHistoryService bookHistoryService;
    private final AuthorService authorService;

    public BookHistoryApplicationServiceImpl(BookHistoryService bookHistoryService, AuthorService authorService) {
        this.bookHistoryService = bookHistoryService;
        this.authorService = authorService;
    }

    @Override
    public List<DisplayBookHistoryDto> findAll() {
        return bookHistoryService.findAll().stream().map(DisplayBookHistoryDto::from).collect(Collectors.toList());
    }

    @Override
    public Optional<DisplayBookHistoryDto> save(CreateBookHistoryDto bookDto) {
        Optional<Author> author=authorService.findById(bookDto.author());
        Category category=bookDto.category();
        return bookHistoryService.save(bookDto.toBook(category,author.get())).map(DisplayBookHistoryDto::from);
    }
}
