package hello.controller;

import com.google.gson.Gson;
import hello.bean.HelloMessage;
import hello.bean.MqttMessage;
import hello.component.mqtt.MqttPublishClient;
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

    MqttPublishClient publishSample;

    @Autowired
    public StompController(MqttPublishClient publishSample) {
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
    public String index(HelloMessage message) throws Exception {
        System.out.println("index()");
//        Thread.sleep(1000); // simulated delay
        MqttMessage msg = new MqttMessage("from webapp", message.getName(), "");
        Gson gson = new Gson();
        return gson.toJson(msg);
    }

    /**
     * Legge un messaggio dal WebSocket e lo invia a MQTT
     *
     * @param message
     * @throws Exception
     */
    @MessageMapping("/toMqtt")
    public void toMqtt(MqttMessage message) {
        System.out.println("toMqtt(): " + message);
        publishSample.send(message.getDestination(), message.getContent());
    }

}
