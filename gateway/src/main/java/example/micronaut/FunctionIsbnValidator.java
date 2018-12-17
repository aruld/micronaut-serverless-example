package example.micronaut;

import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.function.client.FunctionClient;
import io.micronaut.http.annotation.Body;
import io.reactivex.Single;

import javax.inject.Named;

@FunctionClient
@Requires(notEnv = Environment.TEST)
public interface FunctionIsbnValidator extends IsbnValidator {

    @Override
    @Named("isbn-validator")
    Single<IsbnValidationResponse> validateIsbn(@Body IsbnValidationRequest req);
}
