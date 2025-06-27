package mk.ukim.finki.books_lab.jobs;
import mk.ukim.finki.books_lab.service.application.BookApplicationService;
import mk.ukim.finki.books_lab.service.domain.BookService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Component
public class ScheduledTasks {
    private final BookApplicationService bookApplicationService;

    public ScheduledTasks(BookApplicationService bookService) {
        this.bookApplicationService = bookService;
    }

    @Scheduled(cron = "0 0 * * * *")
    public void refreshView() {
        this.bookApplicationService.refreshMaterializedView();
    }
}
