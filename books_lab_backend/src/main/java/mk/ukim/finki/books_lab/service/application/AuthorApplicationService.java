package mk.ukim.finki.books_lab.service.application;

import mk.ukim.finki.books_lab.dto.CreateAuthorDto;
import mk.ukim.finki.books_lab.dto.DisplayAuthorDto;
import mk.ukim.finki.books_lab.model.views.BooksPerAuthor;

import java.util.List;
import java.util.Optional;

public interface AuthorApplicationService {
    List<DisplayAuthorDto> findAll();
    Optional<DisplayAuthorDto> findById(long id);
    void deleteById(long id);
    Optional<DisplayAuthorDto> save(CreateAuthorDto author);
    Optional<DisplayAuthorDto> update(long id,CreateAuthorDto author);

    void refreshMaterializedView();
    Optional<DisplayAuthorDto> findById(Long id);
    List<BooksPerAuthor> findAllBooksPerAuthor();
}
