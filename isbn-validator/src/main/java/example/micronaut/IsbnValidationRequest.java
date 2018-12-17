package example.micronaut;

public class IsbnValidationRequest {
    private String isbn;

    public IsbnValidationRequest(String isbn) {
        this.isbn = isbn;
    }

    public IsbnValidationRequest() {
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
