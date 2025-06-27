package mk.ukim.finki.books_lab.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;


//nov end point logovi od aplikacijata lista od objekti so ke imaat username,token,
// issued at, expired at, za sekoj jwt token so e kreiran od strana na app
@Entity
@Data
public class JwtLogs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private String username;
    private Date issuedAt;
    private Date expiredAt;

    public JwtLogs() {
    }

    public JwtLogs(String token, String username, Date issuedAt, Date expiredAt) {
        this.token = token;
        this.username = username;
        this.issuedAt = issuedAt;
        this.expiredAt = expiredAt;
    }

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(Date issuedAt) {
        this.issuedAt = issuedAt;
    }

    public Date getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(Date expiredAt) {
        this.expiredAt = expiredAt;
    }
}
