package mk.ukim.finki.books_lab.service.application;

import mk.ukim.finki.books_lab.dto.*;

import java.util.List;
import java.util.Optional;

public interface UserApplicationService {
    Optional<DisplayUserDto> register(CreateUserDto createUserDto);
    Optional<LoginResponseDto> login(LoginUserDto loginUserDto);
    Optional<DisplayUserDto> findByUsername(String username);
    List<DisplayBookDto> addBookToWhishlist(String username, Long bookId);
    List<DisplayBookDto> getUserWishlist(String username);
    List<DisplayBookCopyDto> loanWishlistedBooks(String username);
}
