package it.ts.dotcom.malvestio.mqtt.console.component.mqtt;

import it.ts.dotcom.malvestio.mqtt.console.configuration.properties.MqttProperties;
import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author david https://eclipse.org/paho/clients/java/ java -cp
 * target/client-1.0-SNAPSHOT.jar:org.eclipse.paho.client.mqttv3-1.0.2.jar:.
 * it.david.mqtt.client.MqttPublishClient test aaa
 */
@Component
public class MqttPublishClient {

    private static final Logger logger = Logger.getLogger(MqttPublishClient.class);
    
    //http://www.hivemq.com/blog/mqtt-essentials-part-6-mqtt-quality-of-service-levels
    @Autowired
    MqttProperties mqttProperties;

    public void send(String topic, String content) {
        MemoryPersistence persistence = new MemoryPersistence();
        MqttClient sampleClient = null;

        try {

            sampleClient = new MqttClient("tcp://" + mqttProperties.getUrl() + ":" + mqttProperties.getPort(), mqttProperties.getClientPubId(), persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            //http://www.hivemq.com/blog/mqtt-essentials-part-7-persistent-session-queuing-messages
            connOpts.setCleanSession(true);

            if (mqttProperties.getUseCredential()) {
                connOpts.setUserName(mqttProperties.getUsername());
                connOpts.setPassword(mqttProperties.getPassword().toCharArray());
            }

            sampleClient.connect(connOpts);
            logger.info("send(): Publishing message: " + content + " to topic: " + topic);
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(Integer.parseInt(mqttProperties.getQos()));
//            http://www.hivemq.com/blog/mqtt-essentials-part-8-retained-messages
            message.setRetained(Boolean.FALSE);

            sampleClient.publish(topic, message);
            sampleClient.disconnect();
        } catch (MqttException e) {
            logger.info("Error on send with MqttClient...");
        }
    }
}
