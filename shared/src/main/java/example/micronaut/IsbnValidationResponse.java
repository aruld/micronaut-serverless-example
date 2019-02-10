package example.micronaut;

public class IsbnValidationResponse {
    private String isbn;
    private Boolean valid;

    public IsbnValidationResponse() {

    }

    public IsbnValidationResponse(String isbn, Boolean valid) {
        this.isbn = isbn;
        this.valid = valid;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }
}
