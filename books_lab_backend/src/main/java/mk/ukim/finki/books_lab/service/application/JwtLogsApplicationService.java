package mk.ukim.finki.books_lab.service.application;

import mk.ukim.finki.books_lab.dto.DisplayJwtLogDto;

import java.util.List;

public interface JwtLogsApplicationService {
    List<DisplayJwtLogDto> findAll();
}
