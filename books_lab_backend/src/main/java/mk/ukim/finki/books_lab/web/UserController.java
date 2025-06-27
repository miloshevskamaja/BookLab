package mk.ukim.finki.books_lab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.books_lab.dto.*;
import mk.ukim.finki.books_lab.service.application.UserApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name = "Корисничко API", description = "Ендпоинти за автентикација и регистрација на корисници") // Преведен Tag
public class UserController {
    private final UserApplicationService userApplicationService;

    public UserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @Operation(summary = "Регистрирај нов корисник", description = "Креира нов кориснички профил")
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "Корисникот е успешно регистриран"
            ), @ApiResponse(
                    responseCode = "400", description = "Невалиден влез или лозинките не се совпаѓаат"
            )}
    )
    @PostMapping("/register")
    public ResponseEntity<DisplayUserDto> register(@RequestBody CreateUserDto createUserDto) {
        try {
            return userApplicationService.register(createUserDto)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

//    @Operation(summary = "Најава на корисник", description = "Го автентицира корисникот и започнува сесија")
//    @ApiResponses(
//            value = {@ApiResponse(
//                    responseCode = "200",
//                    description = "Корисникот е успешно најавен"
//            ), @ApiResponse(responseCode = "404", description = "Невалидно корисничко име или лозинка")}
//    )
//    @PostMapping("/login")
//    public ResponseEntity<DisplayUserDto> login(HttpServletRequest request) {
//        try {
//            DisplayUserDto displayUserDto = userApplicationService.login(
//                    new LoginUserDto(request.getParameter("username"), request.getParameter("password"))
//            ).orElseThrow(RuntimeException::new);
//
//            request.getSession().setAttribute("user", displayUserDto.toUser());
//            return ResponseEntity.ok(displayUserDto);
//        } catch (RuntimeException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @Operation(summary = "User login", description = "Authenticates a user and generates a JWT")
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "User authenticated successfully"
            ), @ApiResponse(responseCode = "404", description = "Invalid username or password")}
    )
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginUserDto loginUserDto) {
        try {
            return userApplicationService.login(loginUserDto)
                    .map(ResponseEntity::ok)
                    .orElseThrow(RuntimeException::new);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


//    @Operation(summary = "Одјава на корисник", description = "Ја завршува сесијата на корисникот")
//    @ApiResponse(responseCode = "200", description = "Корисникот е успешно одјавен")
//    @GetMapping("/logout")
//    public void logout(HttpServletRequest request) {
//        request.getSession().invalidate();
//    }

    @Operation(summary = "Листа на омилени книги на корисникот", description = "Го враќа списокот на книги од листата на желби на корисникот")
    @ApiResponse(responseCode = "200", description = "Списокот на омилени книги е успешно преземен")
    @GetMapping("/my_wishlist/{username}")
    public List<DisplayBookDto> getUserWishlist(
            @Parameter(description = "Корисничко име") @PathVariable String username) {
        return userApplicationService.getUserWishlist(username);
    }

    @Operation(summary = "Додај книга во листата на желби", description = "Го додава избраниот идентификатор на книга во омилените книги на корисникот")
    @ApiResponse(responseCode = "200", description = "Книгата е успешно додадена во листата на желби")
    @PostMapping("/add_to_wishlist/{username}")
    public List<DisplayBookDto> addBookToWhishlist(
            @Parameter(description = "Корисничко име") @PathVariable String username,
            @Parameter(description = "ID на книгата") @RequestBody Long bookId) {
        return userApplicationService.addBookToWhishlist(username, bookId);
    }

    @Operation(summary = "Изнајми книги од листата на желби", description = "Изнајмува копии од сите книги во листата на желби на корисникот")
    @ApiResponse(responseCode = "200", description = "Книгите се успешно изнајмени")
    @GetMapping("/loan_wishlist/{username}")
    public List<DisplayBookCopyDto> loanUserWishlist(
            @Parameter(description = "Корисничко име") @PathVariable String username) {
        return userApplicationService.loanWishlistedBooks(username);
    }
}