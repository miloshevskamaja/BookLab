package mk.ukim.finki.books_lab.repository;

import mk.ukim.finki.books_lab.model.domain.Author;
import mk.ukim.finki.books_lab.model.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    Optional<Book> findByNameAndAuthor(String name, Author author);

    @Query("SELECT b.relatedBooks FROM Book b WHERE b.id = :bookId")
    List<Book> findRelatedBooks(@Param("bookId") Long bookId);
}
