package hello.controller;

import hello.bean.Greeting;
import hello.bean.HelloMessage;
import hello.bean.Message;
import hello.component.mqtt.MqttPublishSample;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 *
 * @author David
 */
@Controller
public class StompController {

    MqttPublishSample publishSample;

    @Autowired
    public StompController(MqttPublishSample publishSample) {
        this.publishSample = publishSample;
    }

    /**
     * Riceve le richiesta da /app/hello via webSocket. Invia il risultato a
     * /user/topic/greetings
     *
     * @param message
     * @return
     * @throws Exception
     */
    @MessageMapping("/hello")
    @SendTo("/user/topic/greetings")
    public Greeting index(HelloMessage message) throws Exception {
        System.out.println("index()");
//        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }

    /**
     * Legge un messaggio dal WebSocket e lo invia a MQTT
     *
     * @param message
     * @throws Exception
     */
    @MessageMapping("/toMqtt")
    public void toMqtt(Message message) throws Exception {
        System.out.println("toMqtt(): " + message);
        try {
            publishSample.send(message.getDestination(), message.getContent());
        } catch (MqttException ex) {
            System.out.println("Error on publish()" + message.getContent());
        }
    }

}
