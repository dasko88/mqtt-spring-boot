package hello.controller;

import hello.component.mqtt.MqttPublishSample;
import java.util.Date;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class HelloController {

    @Autowired
    MqttPublishSample publishSample;

    @RequestMapping("/")
    public String index() {
        System.out.println("index(): " + new Date());
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/publish")
    public String publish(@RequestParam("topic") String topic, @RequestParam("content") String content) {
        System.out.println("publish(): " + topic + " - " + content);
        try {
            publishSample.send(topic, content);
        } catch (MqttException ex) {
            System.out.println("Error on publish()" + topic + " - " + content);
        }
        return "publish(): Message sent!";
    }

}
