package mk.ukim.finki.books_lab.repository;

import jakarta.transaction.Transactional;
import mk.ukim.finki.books_lab.model.views.BooksPerAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookCountRepository extends JpaRepository<BooksPerAuthor, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW public.books_per_author", nativeQuery = true)
    void refreshMaterializedView();



}
