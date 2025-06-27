package mk.ukim.finki.books_lab.dto;

import mk.ukim.finki.books_lab.model.domain.User;
import mk.ukim.finki.books_lab.model.enumerations.Role;

public record CreateUserDto(
        String username,
        String password,
        String repeatPassword,
        String name,
        String surname,
        Role role
) {
    public User toUser(){
        return new User(username,password,name,surname,role);
    }
}
