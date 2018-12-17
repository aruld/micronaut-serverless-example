package example.micronaut;

import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.security.authentication.*;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Arrays;

@Requires(notEnv = Environment.TEST)
@Singleton
public class SampleAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Publisher<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest) {
        if (authenticationRequest.getIdentity() == null) {
            return Flowable.just(new AuthenticationFailed());
        }
        if (authenticationRequest.getSecret() == null) {
            return Flowable.just(new AuthenticationFailed());
        }
        if (Arrays.asList("sherlock", "watson").contains(authenticationRequest.getIdentity().toString()) && authenticationRequest.getSecret().equals("elementary")) {
            return Flowable.just(new UserDetails(authenticationRequest.getIdentity().toString(), new ArrayList<>()));
        }
        return Flowable.just(new AuthenticationFailed());
    }
}
