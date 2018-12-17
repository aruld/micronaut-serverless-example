package example.micronaut;

import javax.validation.constraints.Pattern;

public interface IsbnValidator {
    boolean isValid(@Pattern(regexp = "\\d{10}") String isbn);
}
