package mk.ukim.finki.books_lab.web;

import io.swagger.v3.oas.annotations.Operation;
import mk.ukim.finki.books_lab.dto.DisplayAuthorDto;
import mk.ukim.finki.books_lab.dto.DisplayCountryDto;
import mk.ukim.finki.books_lab.service.application.CountryApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {
    private final CountryApplicationService countryApplicationService;

    public CountryController(CountryApplicationService countryApplicationService) {
        this.countryApplicationService = countryApplicationService;
    }

    @GetMapping
    @Operation(summary = "Најди ги сите држави", description = "Го враќа списокот на сите држави.")
    public List<DisplayCountryDto> findAll() {
        return countryApplicationService.findAll();
    }
    @GetMapping("{id}")
    @Operation(summary = "Најди држави по ID", description = "Го враќа записот на држави според даденото ID.")
    public ResponseEntity<DisplayCountryDto> findById(@PathVariable Long id){
        return countryApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
