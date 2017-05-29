package hello.component.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.stereotype.Component;

/**
 *
 * @author david https://eclipse.org/paho/clients/java/ java -cp
 * target/client-1.0-SNAPSHOT.jar:org.eclipse.paho.client.mqttv3-1.0.2.jar:.
 * it.david.mqtt.client.MqttPublishClient test aaa
 */
@Component
public class MqttPublishClient {

    int qos = 2;
    String broker = "tcp://localhost:1883";
    String clientId = "JavaSample";

    public void send(String topic, String content) {
        MemoryPersistence persistence = new MemoryPersistence();
        MqttClient sampleClient = null;

        try {
            sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);

            sampleClient.connect(connOpts);
            System.out.println("send(): Publishing message: " + content + " to topic: " + topic);
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);

            sampleClient.publish(topic, message);
            sampleClient.disconnect();
        } catch (MqttException e) {
            System.out.println("Error on send with MqttClient...");
        }
    }
}
