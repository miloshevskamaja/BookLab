package mk.ukim.finki.books_lab.model.enumerations;
import org.springframework.security.core.GrantedAuthority;
public enum Role implements GrantedAuthority{
    ROLE_USER, ROLE_LIBRARIAN;

    @Override
    public String getAuthority() {
        return name();
    }
}
