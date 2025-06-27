package mk.ukim.finki.books_lab.service.application;

import mk.ukim.finki.books_lab.dto.DisplayBookCopyDto;
import mk.ukim.finki.books_lab.dto.DisplayBookDto;

import java.util.List;
import java.util.Optional;

public interface BookCopyApplicationService {
    Optional<DisplayBookCopyDto> createCopy(Long id);
    Optional<DisplayBookCopyDto> findById(Long id);
    List<DisplayBookCopyDto> findAll();
    List<DisplayBookCopyDto> findByBook(Long id);
    Optional<DisplayBookDto> loan(Long id);
    public Optional<DisplayBookCopyDto> returnBook(Long id);

}
