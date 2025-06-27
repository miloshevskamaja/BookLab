package mk.ukim.finki.books_lab.service.domain;

import mk.ukim.finki.books_lab.model.domain.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();
    Optional<Country> findById(long id);
}
