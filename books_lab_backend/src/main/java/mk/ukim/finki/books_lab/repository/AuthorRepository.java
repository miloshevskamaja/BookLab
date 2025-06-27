package mk.ukim.finki.books_lab.repository;

import mk.ukim.finki.books_lab.model.domain.Author;
import mk.ukim.finki.books_lab.model.projections.AuthorName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
    @Query("SELECT a FROM Author a")
    List<AuthorName> findAllProjectedBy();
}
