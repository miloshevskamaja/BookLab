package mk.ukim.finki.books_lab.service.domain.impl;

import mk.ukim.finki.books_lab.model.domain.Country;
import mk.ukim.finki.books_lab.repository.CountryRepository;
import mk.ukim.finki.books_lab.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(long id) {
        return countryRepository.findById(id);
    }
}
