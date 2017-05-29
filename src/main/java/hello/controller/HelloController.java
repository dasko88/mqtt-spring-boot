package hello.controller;

import hello.component.mqtt.MqttPublishClient;
import hello.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    MqttPublishClient publishSample;
    WebSocketService webSocketService;

    @Autowired
    public HelloController(MqttPublishClient publishSample, WebSocketService webSocketService) {
        this.publishSample = publishSample;
        this.webSocketService = webSocketService;
    }

    /**
     * Invia un messaggio via webSocket
     *
     * @param topic
     * @param content
     * @return
     */
    @RequestMapping("/sendToSTOMP")
    public String sendMessage(@RequestParam("topic") String topic, @RequestParam("content") String content) {
        System.out.println("sendMessage(): " + topic + " - " + content);
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
    @RequestMapping("/publishToMQTT")
    public String publish(@RequestParam("topic") String topic, @RequestParam("content") String content) {
        System.out.println("publish(): " + topic + " - " + content);

        publishSample.send(topic, content);

        return "publish(): Message sent!";
    }

    @RequestMapping("/connect")
    public String connect() {
        System.out.println("connect(): ");

//        int result = subscriber.connect();
        int result = 1;

        return result == 1 ? "OK" : "KO";
    }

    @RequestMapping("/disconnect")
    public String disconnect() {
        System.out.println("disconnect(): ");

//        int result = subscriber.disconnect();
        int result = 1;

        return result == 1 ? "OK" : "KO";
    }

    @RequestMapping("/isConnected")
    public String isConnected() {
        System.out.println("isConnected(): ");

//        Boolean result = subscriber.isConnected();
        boolean result = true;

        return result ? "OK" : "KO";
    }

}
