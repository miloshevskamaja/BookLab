package mk.ukim.finki.books_lab.dto;

import mk.ukim.finki.books_lab.model.domain.Country;
import mk.ukim.finki.books_lab.model.domain.JwtLogs;

import java.util.Date;
import java.util.List;

public record DisplayJwtLogDto(
        Long id,
        String token,
        String username,
        Date issuedAt,
        Date expiredAt) {
    public JwtLogs toJwtLogs(){
        return new JwtLogs(token,username,issuedAt,expiredAt);
    }
    public static DisplayJwtLogDto from(JwtLogs jwtLogs){
        return new DisplayJwtLogDto(jwtLogs.getId(),jwtLogs.getToken(),jwtLogs.getUsername(),jwtLogs.getIssuedAt(),jwtLogs.getExpiredAt());
    }
    public static List<DisplayJwtLogDto> from(List<JwtLogs> jwtLogs){
        return jwtLogs.stream()
                .map(c->new DisplayJwtLogDto(c.getId(),c.getToken(),c.getUsername(),c.getIssuedAt(),c.getExpiredAt()))
                .toList();
    }
}
