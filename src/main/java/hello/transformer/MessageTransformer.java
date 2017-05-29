package hello.transformer;

import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.stereotype.Component;

/**
 *
 * @author David
 */
@Component
public class MessageTransformer<S, T> implements GenericTransformer<S, T> {

    @Override
    public T transform(S s) {
        return (T) ("Mio msg trasformato: " + s);
    }

}
