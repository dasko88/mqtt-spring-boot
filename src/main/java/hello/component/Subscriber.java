/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hello.component;

import hello.component.mqtt.MqttSubscriberClient;
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

    MqttSubscriberClient client;

    @PostConstruct
    public void init() {
        connect();
    }

    public int connect() {
        System.out.println("Init Subscriber...");
        client = new MqttSubscriberClient("tcp://localhost:1883", "test/#");

        try {
            return client.connect(webSocketService);
        } catch (MqttException ex) {
            System.out.println("Error with client " + ex.toString());
            return -1;
        }
    }

    public int disconnect() {
        return client.disconnect();
    }

}
