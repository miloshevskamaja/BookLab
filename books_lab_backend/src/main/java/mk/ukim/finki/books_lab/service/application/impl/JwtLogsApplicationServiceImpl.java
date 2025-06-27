package mk.ukim.finki.books_lab.service.application.impl;

import mk.ukim.finki.books_lab.dto.DisplayJwtLogDto;
import mk.ukim.finki.books_lab.service.application.JwtLogsApplicationService;
import mk.ukim.finki.books_lab.service.domain.JwtLogsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JwtLogsApplicationServiceImpl implements JwtLogsApplicationService {
    private final JwtLogsService jwtLogsService;

    public JwtLogsApplicationServiceImpl(JwtLogsService jwtLogsService) {
        this.jwtLogsService = jwtLogsService;
    }

    @Override
    public List<DisplayJwtLogDto> findAll() {
        return this.jwtLogsService.findAll().stream().map(DisplayJwtLogDto::from).toList();
    }
}
