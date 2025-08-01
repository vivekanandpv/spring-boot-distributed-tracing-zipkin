package in.athenaeum.upstream.apis;

import in.athenaeum.upstream.models.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("api/v1/upstream/books")
public class BookApi {
    private final RestTemplate restTemplate;
    private final Logger logger;
    private final String applicationName;

    public BookApi(
            RestTemplate restTemplate,
            @Value("${spring.application.name}")
            String applicationName
    ) {
        this.applicationName = applicationName;
        logger = LoggerFactory.getLogger(this.getClass());
        this.restTemplate = restTemplate;
    }


    @GetMapping
    public ResponseEntity<Map<String, String>> get() {
        logger.info("Incoming request in {} for /api/v1/upstream/books ", applicationName);
        ResponseEntity<Book> responseEntity = restTemplate.getForEntity("/api/v1/downstream/books", Book.class);
        return ResponseEntity.ok(Map.of("book", responseEntity.getBody().toString()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException e) {
        logger.info("Exception caught in handleRuntimeException");
        return ResponseEntity.ok(Map.of("error", e.getMessage()));
    }
}
