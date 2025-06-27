package mk.ukim.finki.books_lab.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.books_lab.model.enumerations.Category;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    private Author author;
    //private Integer availableCopies;

    @ManyToMany
    @JsonIgnore
    private List<Book> relatedBooks=new ArrayList<>();

    private Boolean is_deleted=false;

    @OneToMany(mappedBy = "book")
    private List<BookCopy> bookCopy;


    public Book() {
    }

    public Book(String name, Category category, Author author) {
        this.name = name;
        this.category = category;
        this.author = author;
        //this.availableCopies = availableCopies;
    }

//    public void addRelatedBook(Book book){
//        this.getRelatedBooks().add(this);
//    }

    public void addCopies(BookCopy bookCopy){
        this.getBookCopy().add(bookCopy);
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

    public Long getAuthor() {
        return author.getId();
    }

    public void setAuthor(Author author) {
        this.author = author;
    }


    public List<Book> getRelatedBooks() {
        return relatedBooks;
    }

    public Boolean getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public List<BookCopy> getBookCopy() {
        return bookCopy;
    }

    public void setBookCopy(List<BookCopy> bookCopy) {
        this.bookCopy = bookCopy;
    }

    public Long getId() {
        return id;
    }
}

