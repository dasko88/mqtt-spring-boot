package hello.configuration.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * @author David
 */
@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationProperties {

    @Value("${env}")
    private String env;

    public String getEnv() {
        return env;
    }

    @Override
    public String toString() {
        return "ApplicationProperties{" + "env=" + env + '}';
    }

}
