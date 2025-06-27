package mk.ukim.finki.books_lab.service.application;

import mk.ukim.finki.books_lab.dto.DisplayAuthorDto;
import mk.ukim.finki.books_lab.dto.DisplayCountryDto;
import mk.ukim.finki.books_lab.model.views.AuthorPerCountry;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface CountryApplicationService {
    List<DisplayCountryDto> findAll();
    List<AuthorPerCountry> findAllAuthorsPerCountry();
    void refreshMaterializedView();
    Optional<DisplayCountryDto> findById(long id);

}
