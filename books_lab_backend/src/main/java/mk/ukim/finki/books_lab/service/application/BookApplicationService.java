package mk.ukim.finki.books_lab.service.application;

import mk.ukim.finki.books_lab.dto.CreateBookDto;
import mk.ukim.finki.books_lab.dto.DisplayBookDto;
import mk.ukim.finki.books_lab.model.domain.Book;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface BookApplicationService {
    List<DisplayBookDto> findAll();
    Optional<DisplayBookDto> findById(Long id);
    Optional<DisplayBookDto> update(Long id, CreateBookDto bookDto);
    Optional<DisplayBookDto> save(CreateBookDto bookDto);
    Optional<DisplayBookDto> deleteById(Long id);
    void refreshMaterializedView();




//    Optional<DisplayBookDto> rentBook(Long id, CreateBookDto bookDto);
//    Optional<DisplayBookDto> filterByNameAndAuthor(CreateBookDto createBookDto);
//

}
