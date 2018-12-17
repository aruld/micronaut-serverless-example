package example.micronaut;

import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.server.EmbeddedServer;
import org.junit.Test;

import static org.junit.Assert.*;

public class IsbnValidatorFunctionTest {

    @Test
    public void testFunction() throws Exception {
        EmbeddedServer server = ApplicationContext.run(EmbeddedServer.class);

        IsbnValidatorClient client = server.getApplicationContext().getBean(IsbnValidatorClient.class);

        assertTrue(client.isValid(new IsbnValidationRequest("1491950358")).blockingGet().getValid());
        assertTrue(client.isValid(new IsbnValidationRequest("1680502395")).blockingGet().getValid());
        assertFalse(client.isValid(new IsbnValidationRequest("0000502395")).blockingGet().getValid());
        assertFalse(client.isValid(new IsbnValidationRequest("01234567891")).blockingGet().getValid());
        assertFalse(client.isValid(new IsbnValidationRequest("012345678")).blockingGet().getValid());
        server.stop();
    }
}
