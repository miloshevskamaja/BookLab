package mk.ukim.finki.books_lab.service.domain.impl;

import mk.ukim.finki.books_lab.model.domain.Book;
import mk.ukim.finki.books_lab.model.domain.BookCopy;
import mk.ukim.finki.books_lab.repository.BookRepository;

import mk.ukim.finki.books_lab.service.domain.AuthorService;
import mk.ukim.finki.books_lab.service.domain.BookHistoryService;
import mk.ukim.finki.books_lab.service.domain.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final BookHistoryService bookHistoryService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, BookHistoryService bookHistoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.bookHistoryService = bookHistoryService;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findBtId(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> update(Long id, Book book) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    bookHistoryService.save(existingBook);

                    if(book.getName()!=null){
                        existingBook.setName(book.getName());
                    }
                    return bookRepository.save(existingBook);
                });
    }

    @Override
    public Optional<Book> save(Book book) {
        if(book.getName() !=null &&
        authorService.findById(book.getAuthor()).isPresent() &&
                book.getCategory() !=null
        ){
            return Optional.of(
                    bookRepository.save(new Book(book.getName(),book.getCategory(),authorService.findById(book.getAuthor()).get()))
            );
        }
        return Optional.empty();
    }

    @Override
    public Optional<Book> deleteById(Long id) {
        Book book=this.findBtId(id).get();
        this.bookRepository.delete(book);
        return Optional.of(book);
    }

//    @Override
//    public Optional<Book> rentBook(Long id, BookDto book) {
//        return bookRepository.findById(id)
//                .map(existingBook ->{
//                    if(book.getAvailableCopies()>0){
//                        existingBook.setAvailableCopies(existingBook.getAvailableCopies()-1);
//                    }
//                    return bookRepository.save(existingBook);
//                });
//    }

    @Override
    public Optional<Book> rentBook(Long id, Book book) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    for (BookCopy copy : existingBook.getBookCopy()) {
                        if (copy.getAvailable()) {
                            copy.setAvailable(false);
                            break;
                        }
                    }
                    return bookRepository.save(existingBook);
                });
    }

    @Override
    public Optional<Book> filterByNameAndAuthor(Book book) {
        return authorService.findById(book.getAuthor())
                .flatMap(author -> bookRepository.findByNameAndAuthor(book.getName(),author));
    }

    @Override
    public List<Book> getRelatedBooks(Long bookId){
        return bookRepository.findRelatedBooks(bookId);
    }

    @Override
    public void soft_delete(Long id) {
        Book book = this.bookRepository.findById(id).get();
        book.setIs_deleted(true);
        this.bookRepository.save(book);
    }

}
