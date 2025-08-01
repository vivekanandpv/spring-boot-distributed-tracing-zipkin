package in.athenaeum.downstream.apis;

import in.athenaeum.downstream.models.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/downstream/books")
public class BookApi {
    private final Logger logger;
    private final String applicationName;

    public BookApi(
            @Value("${spring.application.name}")
            String applicationName
    ) {
        this.applicationName = applicationName;
        logger = LoggerFactory.getLogger(this.getClass());
    }

    @GetMapping
    public ResponseEntity<Book> get() {
        logger.info("Incoming request in {} for /api/v1/upstream/books ", applicationName);
        return ResponseEntity.ok(new Book(14, "Learning Spring", 800, 1024));
    }
}
