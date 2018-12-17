package example.micronaut;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.reactivex.Flowable;

@Controller("/api")
@Secured("isAuthenticated()")
public class BooksController {

    private final BooksFetcher booksFetcher;
    private final InventoryFetcher inventoryFetcher;
    private final IsbnValidator isbnValidator;

    public BooksController(BooksFetcher booksFetcher, InventoryFetcher inventoryFetcher, IsbnValidator isbnValidator) {
        this.booksFetcher = booksFetcher;
        this.inventoryFetcher = inventoryFetcher;
        this.isbnValidator = isbnValidator;
    }

    @Get("/books")
    Flowable<Book> findAll() {
        return booksFetcher.fetchBooks()
                .flatMapMaybe(b -> isbnValidator.validateIsbn(new IsbnValidationRequest(b.getIsbn()))
                        .filter(IsbnValidationResponse::getValid)
                        .map(isbnValidationResponse -> b)
                )
                .flatMapMaybe(b -> inventoryFetcher.inventory(b.getIsbn())
                        .filter(stock -> stock > 0)
                        .map(stock -> {
                            b.setStock(stock);
                            return b;
                        })
                );
    }
}

