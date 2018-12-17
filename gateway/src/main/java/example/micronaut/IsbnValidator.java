package example.micronaut;

import io.micronaut.http.annotation.Body;
import io.reactivex.Single;

public interface IsbnValidator {
    Single<IsbnValidationResponse> validateIsbn(@Body IsbnValidationRequest req);
}
