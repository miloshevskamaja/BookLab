package mk.ukim.finki.books_lab.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.books_lab.model.domain.Book;

@Data
@Entity
@Table(name = "book_copy")
public class BookCopy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean isAvailable=true;
    private String state;

    @ManyToOne()
    private Book book;


    public BookCopy() {
    }
    public BookCopy(Book book){
        this.book=book;
        this.isAvailable=true;
    }

    public BookCopy(Boolean isAvailable, String state,Book book) {
        this.isAvailable = isAvailable;
        this.state = state;
        this.book=book;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }
}
