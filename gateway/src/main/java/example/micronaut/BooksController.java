package example.micronaut;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.reactivex.Flowable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

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
    @Operation(summary = "Find all books in store",
            tags = {"books"},
            responses = {
                    @ApiResponse(description = "", content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class, example = "[{\"isbn\":\"1491950358\",\"name\":\"Building Microservices\",\"stock\":2},{\"isbn\":\"1680502395\",\"name\":\"Release It!\",\"stock\":3}]")))}
    )
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