package mk.ukim.finki.books_lab.service.application.impl;

import mk.ukim.finki.books_lab.dto.CreateBookDto;
import mk.ukim.finki.books_lab.dto.DisplayBookDto;
import mk.ukim.finki.books_lab.model.domain.Author;
import mk.ukim.finki.books_lab.model.domain.Book;
import mk.ukim.finki.books_lab.model.enumerations.Category;
import mk.ukim.finki.books_lab.repository.BookCountRepository;
import mk.ukim.finki.books_lab.service.application.BookApplicationService;
import mk.ukim.finki.books_lab.service.domain.AuthorService;
import mk.ukim.finki.books_lab.service.domain.BookHistoryService;
import mk.ukim.finki.books_lab.service.domain.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookApplicationServiceImpl implements BookApplicationService {
    private final BookService bookService;
    private final AuthorService authorService;
    private final BookCountRepository bookCountRepository;

    private final BookHistoryService bookHistoryService;



    public BookApplicationServiceImpl(BookService bookService, AuthorService authorService, BookCountRepository bookCountRepository, BookHistoryService bookHistoryService) {
        this.bookService = bookService;
        this.authorService=authorService;
        this.bookCountRepository = bookCountRepository;
        this.bookHistoryService = bookHistoryService;
    }

    @Override
    public List<DisplayBookDto> findAll() {
        return this.bookService.findAll().stream().map(DisplayBookDto::from).toList();
    }

    @Override
    public Optional<DisplayBookDto> findById(Long id) {
        return this.bookService.findBtId(id).map(DisplayBookDto::from);
    }

    @Override
    public Optional<DisplayBookDto> update(Long id, CreateBookDto createBookDto) {
        Author author= authorService.findById(createBookDto.author()).orElseThrow();
        Category category= createBookDto.category();
        //bookHistoryService.save(createBookDto.toBook(category,author.get()));
        Book book1= createBookDto.toBook(category,author);
        return bookService.update(id,book1).map(DisplayBookDto::from);
    }

    @Override
    public Optional<DisplayBookDto> save(CreateBookDto bookDto) {
        Author author= authorService.findById(bookDto.author()).orElseThrow();
        Category category= bookDto.category();
        Book book1 = bookDto.toBook(category,author);
        return bookService.save(book1).map(DisplayBookDto::from);
    }

    @Override
    public Optional<DisplayBookDto> deleteById(Long id) {
        return bookService.deleteById(id).map(DisplayBookDto::from);
    }

    @Override
    public void refreshMaterializedView() {
        this.bookCountRepository.refreshMaterializedView();
    }


//    @Override
//    public Optional<DisplayBookDto> rentBook(Long id, CreateBookDto bookDto) {
//        return Optional.empty();
//    }
//
//    @Override
//    public Optional<DisplayBookDto> filterByNameAndAuthor(CreateBookDto createBookDto) {
//        return Optional.empty();
//    }
}
