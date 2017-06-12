package it.ts.dotcom.malvestio.mqtt.console.configuration.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * @author David
 */
@Configuration
@PropertySource("classpath:mqtt.properties")
public class MqttProperties {

    @Value("${malvestio.scout.url}")
    private String url;
    @Value("${malvestio.scout.port}")
    private String port;
    @Value("${malvestio.scout.username}")
    private String username;
    @Value("${malvestio.scout.password}")
    private String password;
    @Value("${malvestio.scout.qos}")
    private String qos;
    @Value("${malvestio.scout.pub.clientId}")
    private String clientPubId;
    @Value("${malvestio.scout.sub.clientId}")
    private String clientSubId;
    @Value("${malvestio.scout.sub.topic}")
    private String clientSubTopic;
    @Value("${malvestio.scout.use.credential}")
    private Boolean useCredential;

    public String getUrl() {
        return url;
    }

    public String getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getQos() {
        return qos;
    }

    public String getClientPubId() {
        return clientPubId;
    }

    public String getClientSubId() {
        return clientSubId;
    }

    public String getClientSubTopic() {
        return clientSubTopic;
    }

    public Boolean getUseCredential() {
        return useCredential;
    }

    @Override
    public String toString() {
        return "MqttProperties{" + "url=" + url + ", port=" + port + ", username=" + username + ", password=" + password + ", qos=" + qos + ", clientPubId=" + clientPubId + ", clientSubId=" + clientSubId + ", clientSubTopic=" + clientSubTopic + ", useCredential=" + useCredential + '}';
    }

}
