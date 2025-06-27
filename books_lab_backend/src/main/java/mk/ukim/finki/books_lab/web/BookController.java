package mk.ukim.finki.books_lab.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import mk.ukim.finki.books_lab.dto.CreateBookDto;
import mk.ukim.finki.books_lab.dto.DisplayBookCopyDto;
import mk.ukim.finki.books_lab.dto.DisplayBookDto;
import mk.ukim.finki.books_lab.dto.DisplayBookHistoryDto;
import mk.ukim.finki.books_lab.model.views.BooksPerAuthor;
import mk.ukim.finki.books_lab.repository.BookCountRepository;
import mk.ukim.finki.books_lab.service.application.AuthorApplicationService;
import mk.ukim.finki.books_lab.service.application.BookApplicationService;
import mk.ukim.finki.books_lab.service.application.BookCopyApplicationService;
import mk.ukim.finki.books_lab.service.application.BookHistoryApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {

    private final BookApplicationService bookApplicationService;
    private final AuthorApplicationService authorApplicationService;
    private final BookCopyApplicationService bookCopyApplicationService;
    private final BookHistoryApplicationService historyApplicationService;
    private final BookCountRepository bookCountRepository;

    public BookController(BookApplicationService bookApplicationService, AuthorApplicationService authorApplicationService, BookCopyApplicationService bookCopyApplicationService, BookHistoryApplicationService historyApplicationService, BookCountRepository bookCountRepository) {
        this.bookApplicationService = bookApplicationService;
        this.authorApplicationService = authorApplicationService;
        this.bookCopyApplicationService = bookCopyApplicationService;
        this.historyApplicationService = historyApplicationService;
        this.bookCountRepository = bookCountRepository;
    }

    @GetMapping
    @Operation(summary = "Најди ги сите книги", description = "Го враќа списокот на сите книги.")
    public List<DisplayBookDto> findAll(){
        return bookApplicationService.findAll();
    }

    @GetMapping("{id}")
    @Operation(summary = "Најди книга по ID", description = "Го враќа записот на книга според даденото ID.")
    public ResponseEntity<DisplayBookDto> findById(@PathVariable Long id){
        return bookApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/add")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @Operation(summary = "Додади нова книга", description = "Додава нова книга во системот.")
    public ResponseEntity<DisplayBookDto> save(@RequestBody CreateBookDto createBookDto){
        return bookApplicationService.save(createBookDto)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }
    @PutMapping("/edit/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @Operation(summary = "Ажурирај книга", description = "Го ажурира записот на постоечка книга.")
    public ResponseEntity<DisplayBookDto> update(@PathVariable Long id, @RequestBody CreateBookDto createBookDto){
        return bookApplicationService.update(id,createBookDto)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @Operation(summary = "Избриши книга", description = "Ја брише книгата со даденото ID од системот.")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if(bookApplicationService.findById(id).isPresent()){
            bookApplicationService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/rent/{id}")
    @Operation(summary = "Изнајми книга", description = "Ја изнајмува книгата со даденото ID.")
    public ResponseEntity<DisplayBookDto> rent(@PathVariable Long id){
        return bookCopyApplicationService.loan(id)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping("/createCopy/{id}")
    @PreAuthorize("hasRole('LIBRARIAN')")
    @Operation(summary = "Креирај копија на книга", description = "Создава нова копија на дадена книга.")
    public ResponseEntity<DisplayBookCopyDto> createCopy(@Parameter(description = "ID на книгата") @PathVariable Long id) {
        return bookCopyApplicationService.createCopy(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/bookCopies/{id}")
    @Operation(summary = "Најди ги сите копии на книга", description = "Го враќа списокот на сите копии на дадена книга.")
    public List<DisplayBookCopyDto> findAllCopies(@Parameter(description = "ID на книгата") @PathVariable Long id) {
        return this.bookCopyApplicationService.findByBook(id);
    }

    @GetMapping("/bookEdits/{id}")
    @Operation(summary = "Најди ги сите претходни верзии на книга", description = "Го враќа списокот на сите претходни верзии на дадена книга.")
    public List<DisplayBookHistoryDto> findPrevVersions(@Parameter(description = "ID на книгата") @PathVariable Long id) {
        return this.historyApplicationService.findAll();
    }

    @GetMapping("/by-author")
    public List<BooksPerAuthor> getBooksByAuthor(){
        return authorApplicationService.findAllBooksPerAuthor();
    }





 /* plus functionalities :
    @PostMapping("/filter")
    public ResponseEntity<Book> filterByNameAndAuthor(@RequestBody Book book){
        return bookService.filterByNameAndAuthor(book)
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

//    @GetMapping("/{id}/related")
//    public ResponseEntity<List<Book>> getRelatedBooks(@PathVariable Long id){
//        List<Book> relatedBooks=bookService.getRelatedBooks(id);
//        if(relatedBooks.isEmpty()){
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.ok(relatedBooks);
//    }
    @DeleteMapping("/soft_delete/{id}")
    public ResponseEntity<Book> soft_delete(@PathVariable Long id){
         if(bookService.findBtId(id).isPresent()){
             bookService.soft_delete(id);
             return ResponseEntity.ok().build();
         }
         return ResponseEntity.notFound().build();
    }
*/

}
