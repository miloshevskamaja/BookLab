package mk.ukim.finki.books_lab.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.books_lab.model.enumerations.Category;

@Data
@Entity
//@Table(name = "book_history")
public class BookHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;

    private Long BookId;

    @ManyToOne
    private Author author;

    public BookHistory(){

    }
    public BookHistory(Long bookId,String name, Category category, Author author) {
        this.BookId=bookId;
        this.name = name;
        this.category = category;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
