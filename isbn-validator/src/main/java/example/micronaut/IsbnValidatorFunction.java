package example.micronaut;

import io.micronaut.function.FunctionBean;

import javax.validation.ConstraintViolationException;
import java.util.function.Function;

@FunctionBean("isbn-validator")
public class IsbnValidatorFunction implements Function<IsbnValidationRequest, IsbnValidationResponse> {

    private final IsbnValidator isbnValidator;

    public IsbnValidatorFunction(IsbnValidator isbnValidator) {
        this.isbnValidator = isbnValidator;
    }

    @Override
    public IsbnValidationResponse apply(IsbnValidationRequest isbnValidationRequest) {
        try {
            return new IsbnValidationResponse(isbnValidationRequest.getIsbn(), isbnValidator.isValid(isbnValidationRequest.getIsbn()));
        } catch (ConstraintViolationException e) {
            return new IsbnValidationResponse(isbnValidationRequest.getIsbn(), false);
        }
    }
}
