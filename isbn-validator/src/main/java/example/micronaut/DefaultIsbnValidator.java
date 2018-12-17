package example.micronaut;

import io.micronaut.validation.Validated;

import javax.inject.Singleton;
import javax.validation.constraints.Pattern;

@Singleton
@Validated
public class DefaultIsbnValidator implements IsbnValidator {

    /**
     * must range from 0 to 10 (the symbol X is used for 10), and must be such that the sum of all the ten digits,
     * each multiplied by its (integer) weight, descending from 10 to 1, is a multiple of 11.
     * @param isbn 10 Digit ISBN
     * @return whether the ISBN is valid or not.
     */
    @Override
    public boolean isValid(@Pattern(regexp = "\\d{10}") String isbn) {
        char[] digits = isbn.toCharArray();
        int accumulator = 0;
        int multiplier = 10;
        for (int i = 0; i < digits.length; i++) {
            char c = digits[i];
            accumulator += Character.getNumericValue(c) * multiplier;
            multiplier--;
        }
        return (accumulator % 11 == 0);
    }
}
