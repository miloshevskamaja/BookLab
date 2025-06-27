package mk.ukim.finki.books_lab.service.domain.impl;

import mk.ukim.finki.books_lab.model.domain.Book;
import mk.ukim.finki.books_lab.model.domain.JwtLogs;
import mk.ukim.finki.books_lab.repository.JwtLogsRepository;
import mk.ukim.finki.books_lab.service.domain.JwtLogsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JwtLogsServiceImpl implements JwtLogsService {
    private final JwtLogsRepository jwtLogsRepository;

    public JwtLogsServiceImpl(JwtLogsRepository jwtLogsRepository) {
        this.jwtLogsRepository = jwtLogsRepository;
    }

    @Override
    public List<JwtLogs> findAll() {
        return jwtLogsRepository.findAll();
    }

    @Override
    public Optional<JwtLogs> save(JwtLogs jwtLogs) {
        if(jwtLogs.getToken() !=null &&
                jwtLogs.getUsername()!=null
        ){
            return Optional.of(
                    jwtLogsRepository.save(new JwtLogs(jwtLogs.getToken(), jwtLogs.getUsername(),jwtLogs.getIssuedAt(),jwtLogs.getExpiredAt()))
            );
        }
        return Optional.empty();
    }
}
