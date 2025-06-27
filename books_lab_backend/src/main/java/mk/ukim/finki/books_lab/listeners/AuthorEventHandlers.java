package mk.ukim.finki.books_lab.listeners;

import mk.ukim.finki.books_lab.events.AuthorChangedEvent;
import mk.ukim.finki.books_lab.events.AuthorCreatedEvent;
import mk.ukim.finki.books_lab.events.AuthorDeletedEvent;
import mk.ukim.finki.books_lab.service.application.CountryApplicationService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AuthorEventHandlers {
    private final CountryApplicationService countryApplicationService;

    public AuthorEventHandlers(CountryApplicationService countryApplicationService) {
        this.countryApplicationService = countryApplicationService;
    }
    @EventListener
    public void onAuthorCreated(AuthorCreatedEvent event) {
        this.countryApplicationService.refreshMaterializedView();
    }
    @EventListener
    public void onAuthorDeleted(AuthorDeletedEvent event) {
        this.countryApplicationService.refreshMaterializedView();
    }
    @EventListener
    public void onAuthorChanged(AuthorChangedEvent event) {
        this.countryApplicationService.refreshMaterializedView();
    }
}
