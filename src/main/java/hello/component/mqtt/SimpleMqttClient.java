package hello.component.mqtt;

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
        MqttCallbackImpl callbackImpl = new MqttCallbackImpl();

        myClient = new MqttClient(brokerUrl, "webapp");
        myClient.setCallback(callbackImpl);
        myClient.connect();

        System.out.println("subscriber(): Connected to " + brokerUrl + " topic " + topic);

        int subQoS = 0;
        myClient.subscribe(topic, subQoS);

//        myClient.disconnect();
    }
}
