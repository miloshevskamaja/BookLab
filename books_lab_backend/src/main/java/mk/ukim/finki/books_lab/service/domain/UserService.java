package mk.ukim.finki.books_lab.service.domain;

import mk.ukim.finki.books_lab.model.domain.Book;
import mk.ukim.finki.books_lab.model.domain.BookCopy;
import mk.ukim.finki.books_lab.model.domain.User;
import mk.ukim.finki.books_lab.model.enumerations.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User register(String username, String password, String repeatPassword, String name, String surname, Role role);

    User login(String username, String password);

    User findByUsername(String username);

    List<Book> addBookToWhishlist(String username, Long bookId);
    List<Book> getUserWishlist(String username);
    List<BookCopy> loanWishlistedBooks(String username);
}
