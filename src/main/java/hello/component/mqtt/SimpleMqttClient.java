package hello.component.mqtt;

import java.util.Date;
import org.eclipse.paho.client.mqttv3.*;

/**
 *
 * @author david https://gist.github.com/m2mIO-gister/5275324
 */
public class SimpleMqttClient {

    private final String brokerUrl;
    private final String topic;

    MqttClient myClient;

    public SimpleMqttClient(String brokerUrl, String topic) {
        this.brokerUrl = brokerUrl;
        this.topic = topic;
    }

    public void runClient() throws MqttException {
        try {
            Date date = new Date();
            myClient = new MqttClient(brokerUrl, "webapp_" + date.getTime());

            MqttCallbackImpl callbackImpl = new MqttCallbackImpl();
            myClient.setCallback(callbackImpl);
            myClient.connect();

            System.out.println("runClient(): Connected to " + brokerUrl + " topic " + topic);

            int subQoS = 0;
            myClient.subscribe(topic, subQoS);
        } catch (Exception e) {
            System.out.println("Error in runClient()\n" + e);
            myClient.disconnect();
        }
    }
}
