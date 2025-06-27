package mk.ukim.finki.books_lab.service.domain.impl;

import mk.ukim.finki.books_lab.model.domain.Book;
import mk.ukim.finki.books_lab.model.domain.BookCopy;
import mk.ukim.finki.books_lab.repository.BookCopyRepository;
import mk.ukim.finki.books_lab.service.domain.BookCopyService;
import mk.ukim.finki.books_lab.service.domain.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookCopyServiceImpl implements BookCopyService {
    private final BookCopyRepository bookCopyRepository;
    private final BookService bookService;

    public BookCopyServiceImpl(BookCopyRepository bookCopyRepository, BookService bookService) {
        this.bookCopyRepository = bookCopyRepository;
        this.bookService = bookService;
    }


    @Override
    public Optional<BookCopy> createCopy(Long id) {
        Book book = bookService.findBtId(id).get();
        BookCopy bookCopy = new BookCopy(book);
        bookCopyRepository.save(bookCopy);
        return Optional.of(bookCopy);
    }

    @Override
    public Optional<BookCopy> findById(Long id) {
        return Optional.of(bookCopyRepository.findById(id).get());
    }

    @Override
    public List<BookCopy> findAll() {
        return bookCopyRepository.findAll();
    }

    @Override
    public List<BookCopy> findByBook(Long id) {
        Book book=bookService.findBtId(id).get();
        return this.findAll().stream().filter(bookCopy -> bookCopy.getBook().equals(book)).collect(Collectors.toList());
    }

    @Override
    public Optional<Book> loan(Long id) {
        BookCopy bookCopy=bookCopyRepository.findById(id).get();
        bookCopy.setAvailable(false);
        return Optional.of(bookCopy.getBook());
    }

    @Override
    public Optional<BookCopy> returnBook(Long id) {
        BookCopy bookCopy=bookCopyRepository.findById(id).get();
        bookCopy.setAvailable(true);
        return Optional.of(bookCopy);
    }
}
