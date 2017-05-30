package hello.configuration.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * @author David
 */
@Configuration
@PropertySource("classpath:database.properties")
public class DatabaseProperties {

    @Value("${malvestio.scout.database.url}")
    private String url;

    @Value("${malvestio.scout.database.driver}")
    private String driver;

    @Value("${malvestio.scout.database.username}")
    private String username;

    @Value("${malvestio.scout.database.password}")
    private String password;

    public String getUrl() {
        return url;
    }

    public String getDriver() {
        return driver;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "ApplicationProperties{" + "url=" + url + ", driver=" + driver + ", username=" + username + ", password=" + password + '}';
    }

}
