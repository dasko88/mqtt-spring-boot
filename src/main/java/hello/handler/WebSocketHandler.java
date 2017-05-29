package hello.handler;

import com.google.gson.Gson;
import hello.bean.MqttMessage;
import hello.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.handler.AbstractMessageHandler;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

/**
 *
 * @author David
 */
@Component
public class WebSocketHandler extends AbstractMessageHandler {

    @Autowired
    WebSocketService webSocketService;

    @Override
    protected void handleMessageInternal(Message<?> msg) throws Exception {
        MessageHeaders headers = msg.getHeaders();
        String topic = (String) headers.get("mqtt_topic");
        MqttMessage mqttMessage = new MqttMessage(topic, (String) msg.getPayload(), "handler");

        Gson gson = new Gson();
        try {
            System.out.println("handler(): sending " + mqttMessage + " from MQTT to WebSocket...");
            webSocketService.sendMessage("/user/topic/greetings", gson.toJson(mqttMessage));
        } catch (Exception e) {
            System.out.println("handler(): error on sending msg from MQTT to WebSocket...");
            e.printStackTrace();
        }
    }

}
