package mk.ukim.finki.books_lab.service.application.impl;

import mk.ukim.finki.books_lab.dto.DisplayAuthorDto;
import mk.ukim.finki.books_lab.dto.DisplayCountryDto;
import mk.ukim.finki.books_lab.model.views.AuthorPerCountry;
import mk.ukim.finki.books_lab.repository.AuthorCountByCountryRepository;
import mk.ukim.finki.books_lab.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryApplicationService implements mk.ukim.finki.books_lab.service.application.CountryApplicationService {
   private final AuthorCountByCountryRepository authorCountByCountryRepository;
   private final CountryService countryService;

    public CountryApplicationService(AuthorCountByCountryRepository authorCountByCountryRepository, CountryService countryService) {
        this.authorCountByCountryRepository = authorCountByCountryRepository;
        this.countryService = countryService;
    }

    @Override
    public List<DisplayCountryDto> findAll() {
        return countryService.findAll().stream()
                .map(DisplayCountryDto::from)
                .toList();
    }

    @Override
    public List<AuthorPerCountry> findAllAuthorsPerCountry() {
        return authorCountByCountryRepository.findAll();
    }

    @Override
    public void refreshMaterializedView() {
         authorCountByCountryRepository.refreshMaterializedView();
    }

    @Override
    public Optional<DisplayCountryDto> findById(long id) {
        return countryService.findById(id).map(DisplayCountryDto::from);
    }
}
