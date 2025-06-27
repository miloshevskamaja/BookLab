package mk.ukim.finki.books_lab.service.application.impl;

import mk.ukim.finki.books_lab.dto.*;
import mk.ukim.finki.books_lab.model.domain.Book;
import mk.ukim.finki.books_lab.model.domain.JwtLogs;
import mk.ukim.finki.books_lab.model.domain.User;
import mk.ukim.finki.books_lab.security.JwtHelper;
import mk.ukim.finki.books_lab.service.application.UserApplicationService;
import mk.ukim.finki.books_lab.service.domain.JwtLogsService;
import mk.ukim.finki.books_lab.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {
    private final UserService userService;
    private final JwtHelper jwtHelper;
    private final JwtLogsService jwtLogsService;

    public UserApplicationServiceImpl(UserService userService, JwtHelper jwtHelper, JwtLogsService jwtLogsService) {
        this.userService = userService;
        this.jwtHelper = jwtHelper;
        this.jwtLogsService = jwtLogsService;
    }

    @Override
    public Optional<DisplayUserDto> register(CreateUserDto createUserDto) {
        User user = userService.register(createUserDto.username(), createUserDto.password(), createUserDto.repeatPassword(), createUserDto.name(), createUserDto.surname(), createUserDto.role());
        return Optional.of(DisplayUserDto.from(user));
    }

    @Override
    public Optional<LoginResponseDto> login(LoginUserDto loginUserDto) {
        User user = userService.login(
                loginUserDto.username(),
                loginUserDto.password()
        );

        String token = jwtHelper.generateToken(user);

        Date expiredAt=jwtHelper.extractExpiration(token);
        Date issuedAt=jwtHelper.issuedAt(token);
        jwtLogsService.save(new JwtLogs(token,loginUserDto.username(),issuedAt,expiredAt));

        return Optional.of(new LoginResponseDto(token));
    }

    @Override
    public Optional<DisplayUserDto> findByUsername(String username) {
        return Optional.of(DisplayUserDto.from(userService.findByUsername(username)));
    }

    @Override
    public List<DisplayBookDto> addBookToWhishlist(String username, Long bookId) {
        User user = userService.findByUsername(username);
        userService.addBookToWhishlist(username, bookId);
        List<Book> books = user.getBookList();
        return books.stream().map(DisplayBookDto::from).collect(Collectors.toList());
    }

    @Override
    public List<DisplayBookDto> getUserWishlist(String username) {
        return userService.getUserWishlist(username).stream().map(DisplayBookDto::from).collect(Collectors.toList());
    }

    @Override
    public List<DisplayBookCopyDto> loanWishlistedBooks(String username) {
        return userService.loanWishlistedBooks(username).stream().map(DisplayBookCopyDto::from).collect(Collectors.toList());
    }
}
