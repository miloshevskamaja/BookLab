package mk.ukim.finki.books_lab.service.application.impl;

import mk.ukim.finki.books_lab.dto.CreateAuthorDto;
import mk.ukim.finki.books_lab.dto.DisplayAuthorDto;
import mk.ukim.finki.books_lab.dto.DisplayCountryDto;
import mk.ukim.finki.books_lab.events.AuthorChangedEvent;
import mk.ukim.finki.books_lab.events.AuthorCreatedEvent;
import mk.ukim.finki.books_lab.events.AuthorDeletedEvent;
import mk.ukim.finki.books_lab.model.domain.Author;
import mk.ukim.finki.books_lab.model.domain.Country;
import mk.ukim.finki.books_lab.model.views.BooksPerAuthor;
import mk.ukim.finki.books_lab.repository.BookCountRepository;
import mk.ukim.finki.books_lab.service.application.AuthorApplicationService;
import mk.ukim.finki.books_lab.service.application.CountryApplicationService;
import mk.ukim.finki.books_lab.service.domain.AuthorService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorApplicationServiceImpl implements AuthorApplicationService {
    private final AuthorService authorService;
    private final BookCountRepository bookCountRepository;
    private final CountryApplicationService countryApplicationService;

    private final ApplicationEventPublisher applicationEventPublisher;


    public AuthorApplicationServiceImpl(AuthorService authorService, BookCountRepository bookCountRepository, CountryApplicationService countryApplicationService, ApplicationEventPublisher applicationEventPublisher) {
        this.authorService = authorService;
        this.bookCountRepository = bookCountRepository;
        this.countryApplicationService = countryApplicationService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public List<DisplayAuthorDto> findAll() {
        return authorService.findAll().stream()
                .map(DisplayAuthorDto::from)
                .toList();
    }

    @Override
    public Optional<DisplayAuthorDto> findById(long id) {
        return authorService.findById(id).map(DisplayAuthorDto::from);
    }

    @Override
    public void deleteById(long id) {
        Author author =authorService.findById(id).orElseThrow();
        authorService.deleteById(id);

        this.applicationEventPublisher.publishEvent(new AuthorDeletedEvent(author));
    }

    @Override
    public Optional<DisplayAuthorDto> save(CreateAuthorDto author) {
        Optional<DisplayCountryDto> country = countryApplicationService.findById(author.country());
        Author author1 = author.toAuthor(country.orElse(null).toCountry());

        this.applicationEventPublisher.publishEvent(new AuthorCreatedEvent(author1));

        return authorService.save(author1).map(DisplayAuthorDto::from);
    }

    @Override
    public Optional<DisplayAuthorDto> update(long id, CreateAuthorDto author) {
        Country country = countryApplicationService.findById(author.country()).orElseThrow().toCountry();
        Author author1 = author.toAuthor(country);
        this.applicationEventPublisher.publishEvent(new AuthorChangedEvent(author1));

        return authorService.update(id, author1).map(DisplayAuthorDto::from);
    }

    @Override
    public void refreshMaterializedView() {

    }

    @Override
    public Optional<DisplayAuthorDto> findById(Long id) {
        return this.authorService.findById(id).map(DisplayAuthorDto::from);
    }

    @Override
    public List<BooksPerAuthor> findAllBooksPerAuthor() {
        return bookCountRepository.findAll();
    }
}
