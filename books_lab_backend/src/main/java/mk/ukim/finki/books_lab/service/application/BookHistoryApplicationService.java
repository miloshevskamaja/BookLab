package mk.ukim.finki.books_lab.service.application;

import mk.ukim.finki.books_lab.dto.CreateBookDto;
import mk.ukim.finki.books_lab.dto.CreateBookHistoryDto;
import mk.ukim.finki.books_lab.dto.DisplayBookDto;
import mk.ukim.finki.books_lab.dto.DisplayBookHistoryDto;

import java.util.List;
import java.util.Optional;

public interface BookHistoryApplicationService {
    List<DisplayBookHistoryDto> findAll();
    Optional<DisplayBookHistoryDto> save(CreateBookHistoryDto bookDto);
}
