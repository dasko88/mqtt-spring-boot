/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hello.component;

import hello.component.mqtt.SimpleMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Component;

/**
 *
 * @author david
 */
@Component
public class Subscriber {

    public Subscriber() {
        System.out.println("Init Subscriber()...");
        SimpleMqttClient client = new SimpleMqttClient("tcp://localhost:1883", "test/#");
        try {
            client.runClient();
        } catch (MqttException ex) {
            System.out.println("Error with client " + ex.toString());
        }
    }

}
