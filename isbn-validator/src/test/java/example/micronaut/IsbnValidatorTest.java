package example.micronaut;

import io.micronaut.context.ApplicationContext;
import io.micronaut.context.DefaultApplicationContext;
import io.micronaut.context.env.Environment;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.validation.ConstraintViolationException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IsbnValidatorTest {
    private static ApplicationContext applicationContext;

    @BeforeClass
    public static void setupContext() {
        applicationContext = new DefaultApplicationContext(Environment.TEST).start();
    }

    @AfterClass
    public static void stopContext() {
        if (applicationContext != null) {
            applicationContext.stop();
        }
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testTenDigitValidation() {
        thrown.expect(ConstraintViolationException.class);

        IsbnValidator isbnValidator = applicationContext.getBean(IsbnValidator.class);
        isbnValidator.isValid("01234567891");
    }

    @Test
    public void testControlDigitValidationWorks() {
        IsbnValidator isbnValidator = applicationContext.getBean(IsbnValidator.class);
        assertTrue(isbnValidator.isValid("1491950358"));
        assertTrue(isbnValidator.isValid("1680502395"));
        assertFalse(isbnValidator.isValid("0000502395"));
    }


}
