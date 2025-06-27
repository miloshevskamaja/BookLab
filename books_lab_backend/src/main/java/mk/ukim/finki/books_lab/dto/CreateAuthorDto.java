package mk.ukim.finki.books_lab.dto;

import mk.ukim.finki.books_lab.model.domain.Author;
import mk.ukim.finki.books_lab.model.domain.Country;

import java.util.List;

public record CreateAuthorDto(String name, String surname, Long country) {
    public Author toAuthor(Country country){
        return new Author(name,surname,country);
    }
    public static CreateAuthorDto from(Author author){
        return new CreateAuthorDto(author.getName(),author.getSurname(),author.getCountry().getId());
    }
    public static List<CreateAuthorDto> from(List<Author> authors){
        return authors.stream()
                .map(a->new CreateAuthorDto(a.getName(),a.getSurname(),a.getCountry().getId()))
                .toList();
    }
}
