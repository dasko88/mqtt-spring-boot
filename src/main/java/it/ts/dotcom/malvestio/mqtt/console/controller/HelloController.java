package it.ts.dotcom.malvestio.mqtt.console.controller;

import io.swagger.annotations.Api;
import it.ts.dotcom.malvestio.mqtt.console.component.mqtt.MqttPublishClient;
import it.ts.dotcom.malvestio.mqtt.console.service.WebSocketService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "esempi", description = "esempi generici")
@RestController
public class HelloController {

    private static final Logger logger = Logger.getLogger(HelloController.class);

    @Autowired
    MqttPublishClient publishSample;

    @Autowired
    WebSocketService webSocketService;

    /**
     * Invia un messaggio via webSocket
     *
     * @param topic
     * @param content
     * @return
     */
    @RequestMapping(value = "/sendToSTOMP", method = RequestMethod.GET)
    public String sendMessage(@RequestParam("topic") String topic, @RequestParam("content") String content) {
        logger.trace("sendMessage(): " + topic + " - " + content);
        webSocketService.sendMessage(topic, content);
        return "Greetings from Spring Boot!";
    }

    /**
     * Invia un messaggio via MQTT
     *
     * @param topic
     * @param content
     * @return
     */
    @RequestMapping(value = "/publishToMQTT", method = RequestMethod.GET)
    public String publish(@RequestParam("topic") String topic, @RequestParam("content") String content) {
        logger.trace("publish(): " + topic + " - " + content);

        publishSample.send(topic, content);

        return "publish(): Message sent!";
    }

    @RequestMapping(value = "/disconnect", method = RequestMethod.GET)
    public String disconnect() {
        logger.trace("disconnect(): ");

//        int result = subscriber.disconnect();
        int result = 1;

        return result == 1 ? "OK" : "KO";
    }

    @RequestMapping(value = "/isConnected", method = RequestMethod.GET)
    public String isConnected() {
        logger.trace("isConnected(): ");

//        Boolean result = subscriber.isConnected();
        boolean result = true;

        return result ? "OK" : "KO";
    }

}
