package mk.ukim.finki.books_lab.service.domain;

import mk.ukim.finki.books_lab.model.domain.JwtLogs;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface JwtLogsService {
    List<JwtLogs> findAll();
    Optional<JwtLogs> save(JwtLogs jwtLogs);
}
