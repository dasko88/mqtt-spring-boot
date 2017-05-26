package hello.component.mqtt;

import hello.service.WebSocketService;
import java.util.Date;
import org.eclipse.paho.client.mqttv3.*;

/**
 *
 * @author david https://gist.github.com/m2mIO-gister/5275324
 */
public class MqttSubscriberClient {

    private final String brokerUrl;
    private final String topic;

    MqttClient myClient;

    public MqttSubscriberClient(String brokerUrl, String topic) {
        this.brokerUrl = brokerUrl;
        this.topic = topic;
    }

    public int connect(WebSocketService webSocketService) throws MqttException {
        try {
            Date date = new Date();
            myClient = new MqttClient(brokerUrl, "webapp_" + date.getTime());

            MqttCallbackImpl callbackImpl = new MqttCallbackImpl(webSocketService);
            myClient.setCallback(callbackImpl);
            myClient.connect();

            int subQoS = 0;
            myClient.subscribe(topic, subQoS);

            System.out.println("Subscriber connected to " + brokerUrl + " topic " + topic);
            return 1;
        } catch (Exception e) {
            System.out.println("Error in runClient()...");
            e.printStackTrace();
            myClient.disconnect();
            return -1;
        }
    }

    public int disconnect() {
        try {
            myClient.disconnect();
            System.out.println("Subscriber disconnected...");
        } catch (Exception e) {
            System.out.println("Error on disconnect()...");
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    public Boolean isConnected() {
        Boolean result;
        try {
            result = myClient.isConnected();
            System.out.println("Subscriber connected " + result);
        } catch (Exception e) {
            System.out.println("Error on isConnected()...");
            e.printStackTrace();
            return null;
        }
        return result;
    }
}
