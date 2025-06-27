package mk.ukim.finki.books_lab.service.domain.impl;

import mk.ukim.finki.books_lab.model.domain.Book;
import mk.ukim.finki.books_lab.model.domain.BookHistory;
import mk.ukim.finki.books_lab.repository.BookHistoryRepository;
import mk.ukim.finki.books_lab.service.domain.AuthorService;
import mk.ukim.finki.books_lab.service.domain.BookHistoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookHistoryServiceImpl implements BookHistoryService {
    private final BookHistoryRepository bookHistoryRepository;
    private final AuthorService authorService;

    public BookHistoryServiceImpl(BookHistoryRepository bookHistoryRepository, AuthorService authorService) {
        this.bookHistoryRepository = bookHistoryRepository;
        this.authorService = authorService;
    }

    @Override
    public List<BookHistory> findAll() {
        return bookHistoryRepository.findAll();
    }

    @Override
    public Optional<BookHistory> save(Book book) {
        if(book.getName() !=null &&
                authorService.findById(book.getAuthor()).isPresent() &&
                book.getCategory() !=null
        ){
            return Optional.of(
                    bookHistoryRepository.save(new BookHistory(book.getId(),book.getName(),book.getCategory(),authorService.findById(book.getAuthor()).get()))
            );
        }
        return Optional.empty();
    }
}
