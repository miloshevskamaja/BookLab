package mk.ukim.finki.books_lab.dto;

import mk.ukim.finki.books_lab.model.domain.Author;

public record DisplayAuthorDto(
        Long id,
        String name,
        String surname,
        Long country
) {
    public static DisplayAuthorDto from(Author author){
        return new DisplayAuthorDto(
                author.getId(),
                author.getName(),
                author.getSurname(),
                author.getCountry().getId()
        );
    }


}
