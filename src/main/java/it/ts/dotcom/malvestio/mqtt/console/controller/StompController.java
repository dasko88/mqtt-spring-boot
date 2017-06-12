package it.ts.dotcom.malvestio.mqtt.console.controller;

import com.google.gson.Gson;
import it.ts.dotcom.malvestio.mqtt.console.bean.HelloMessage;
import it.ts.dotcom.malvestio.mqtt.console.bean.MqttMessage;
import it.ts.dotcom.malvestio.mqtt.console.component.mqtt.MqttPublishClient;
import java.util.Date;
import org.apache.log4j.Logger;
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

    private static final Logger logger = Logger.getLogger(StompController.class);
    
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
        logger.trace("index()");
//        Thread.sleep(1000); // simulated delay
        MqttMessage msg = new MqttMessage("from webapp", message.getName(), "", new Date());
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
        logger.trace("toMqtt(): " + message);
        publishSample.send(message.getDestination(), message.getContent());
    }

}
