package mk.ukim.finki.books_lab.repository;

import mk.ukim.finki.books_lab.model.domain.BookHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookHistoryRepository extends JpaRepository<BookHistory,Long> {
}
