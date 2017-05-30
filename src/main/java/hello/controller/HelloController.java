package hello.controller;

import hello.component.mqtt.MqttPublishClient;
import hello.model.mapper.UserMapper;
import hello.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    MqttPublishClient publishSample;

    @Autowired
    WebSocketService webSocketService;

    @Autowired
    UserMapper userMapper;

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

    @RequestMapping(value = "/connect/{id}", method = RequestMethod.GET, produces = "application/json")
    public String connect(@PathVariable Integer id) {
        System.out.println("connect(): " + userMapper.get(id).getEmail());

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
