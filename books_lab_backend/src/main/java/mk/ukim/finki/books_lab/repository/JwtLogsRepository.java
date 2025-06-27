package mk.ukim.finki.books_lab.repository;

import mk.ukim.finki.books_lab.model.domain.JwtLogs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JwtLogsRepository extends JpaRepository<JwtLogs, Long> {

}
