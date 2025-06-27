package mk.ukim.finki.books_lab.dto;

import mk.ukim.finki.books_lab.model.domain.User;
import mk.ukim.finki.books_lab.model.enumerations.Role;

public record DisplayUserDto(
        String username,
        String name,
        String surname,
        Role role
) {
    public static DisplayUserDto from(User user){
        return new DisplayUserDto(user.getUsername(), user.getName(), user.getSurname(), user.getRole());
    }
    public User toUser(){
        return new User(username,name,surname, role().name());
    }
}
