package mk.ukim.finki.books_lab.web;

import io.swagger.v3.oas.annotations.Operation;
import mk.ukim.finki.books_lab.dto.CreateAuthorDto;
import mk.ukim.finki.books_lab.dto.CreateBookDto;
import mk.ukim.finki.books_lab.dto.DisplayAuthorDto;
import mk.ukim.finki.books_lab.dto.DisplayBookDto;
import mk.ukim.finki.books_lab.model.views.AuthorPerCountry;
import mk.ukim.finki.books_lab.service.application.AuthorApplicationService;
import mk.ukim.finki.books_lab.service.application.CountryApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final CountryApplicationService countryApplicationService;
    private final AuthorApplicationService authorApplicationService;


    public AuthorController(CountryApplicationService countryApplicationService, AuthorApplicationService authorApplicationService) {
        this.countryApplicationService = countryApplicationService;

        this.authorApplicationService = authorApplicationService;
    }

    @GetMapping
    @Operation(summary = "Најди ги сите автори", description = "Го враќа списокот на сите книги.")
    public List<DisplayAuthorDto> findAll(){
        return authorApplicationService.findAll();
    }

//    @GetMapping("/names")
//    public List<AuthorName> getAuthorNames() {
//        return authorRepository.findAllProjectedBy();
//    }

    @GetMapping("{id}")
    @Operation(summary = "Најди автор по ID", description = "Го враќа записот на автор според даденото ID.")
    public ResponseEntity<DisplayAuthorDto> findById(@PathVariable Long id){
        return authorApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @Operation(summary = "Додади нова книга", description = "Додава нова книга во системот.")
    public ResponseEntity<DisplayAuthorDto> save(@RequestBody CreateAuthorDto createAuthorDto){
        return authorApplicationService.save(createAuthorDto)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping("/by-country")
    public List<AuthorPerCountry> getAuthorsByCountry() {
        return countryApplicationService.findAllAuthorsPerCountry();
    }
}
