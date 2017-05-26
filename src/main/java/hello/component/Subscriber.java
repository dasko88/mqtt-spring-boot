/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hello.component;

import hello.component.mqtt.SimpleMqttClient;
import hello.service.WebSocketService;
import javax.annotation.PostConstruct;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author david
 */
@Component
public class Subscriber {

    @Autowired
    WebSocketService webSocketService;

    @PostConstruct
    public void init() {
        System.out.println("Init Subscriber()...");
        SimpleMqttClient client = new SimpleMqttClient("tcp://localhost:1883", "test/#");

        try {
            client.runClient(webSocketService);
        } catch (MqttException ex) {
            System.out.println("Error with client " + ex.toString());
        }
    }

}
