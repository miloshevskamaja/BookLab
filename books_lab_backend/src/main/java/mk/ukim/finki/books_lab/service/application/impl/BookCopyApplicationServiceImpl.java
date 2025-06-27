package mk.ukim.finki.books_lab.service.application.impl;

import mk.ukim.finki.books_lab.dto.DisplayBookCopyDto;
import mk.ukim.finki.books_lab.dto.DisplayBookDto;
import mk.ukim.finki.books_lab.model.domain.Book;
import mk.ukim.finki.books_lab.model.domain.BookCopy;
import mk.ukim.finki.books_lab.service.application.BookCopyApplicationService;
import mk.ukim.finki.books_lab.service.domain.BookCopyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookCopyApplicationServiceImpl implements BookCopyApplicationService {
    private final BookCopyService bookCopyService;

    public BookCopyApplicationServiceImpl(BookCopyService bookCopyService) {
        this.bookCopyService = bookCopyService;
    }


    @Override
    public Optional<DisplayBookCopyDto> createCopy(Long id) {
        return bookCopyService.createCopy(id).map(DisplayBookCopyDto::from);

    }

    @Override
    public Optional<DisplayBookCopyDto> findById(Long id) {
        return bookCopyService.findById(id).map(DisplayBookCopyDto::from);
    }

    @Override
    public List<DisplayBookCopyDto> findAll() {
        return bookCopyService.findAll().stream().map(DisplayBookCopyDto::from).toList();
    }

    @Override
    public List<DisplayBookCopyDto> findByBook(Long id) {
        return bookCopyService.findByBook(id).stream().map(DisplayBookCopyDto::from).toList();
    }

    @Override
    public Optional<DisplayBookDto> loan(Long id) {
        return bookCopyService.loan(id).map(DisplayBookDto::from);
    }

    @Override
    public Optional<DisplayBookCopyDto> returnBook(Long id) {
        return bookCopyService.returnBook(id).map(DisplayBookCopyDto::from);
    }


}
