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

    //http://www.hivemq.com/blog/mqtt-essentials-part-6-mqtt-quality-of-service-levels
    int qos = 2;
    // qos = 0 -> il msg viene inviato al più una volta. Fire and forget.
    // qos = 1 -> il msg viene inviato almeno una volta. Il sender manda una prima volta il msg e rimane in ascolto di un msg di conferma PUBACK. Se non arriva, dopo un certo tempo re-invia il msg
    // qos = 2 -> il msg viene inviato esattamente una volta. 
    // The QoS level for publishing client to broker is depending on the QoS level the client sets for the particular message. When the broker transfers a message to a subscribing client it uses the QoS of the subscription made by the client earlier. 
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
