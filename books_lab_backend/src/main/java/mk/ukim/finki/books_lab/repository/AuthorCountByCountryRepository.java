package mk.ukim.finki.books_lab.repository;


import jakarta.transaction.Transactional;
import mk.ukim.finki.books_lab.model.views.AuthorPerCountry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorCountByCountryRepository extends JpaRepository<AuthorPerCountry, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW public.authors_per_country", nativeQuery = true)
    void refreshMaterializedView();
}
