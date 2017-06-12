package it.ts.dotcom.malvestio.mqtt.console.handler;

import com.google.gson.Gson;
import it.ts.dotcom.malvestio.mqtt.console.bean.MqttMessage;
import it.ts.dotcom.malvestio.mqtt.console.service.WebSocketService;
import java.util.Date;
import org.apache.log4j.Logger;
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

    private static final Logger logger = Logger.getLogger(WebSocketHandler.class);

    @Autowired
    WebSocketService webSocketService;

    @Override
    protected void handleMessageInternal(Message<?> msg) throws Exception {
        MessageHeaders headers = msg.getHeaders();
        String topic = (String) headers.get("mqtt_topic");
        MqttMessage mqttMessage = new MqttMessage(topic, (String) msg.getPayload(), "handler", new Date());

        Gson gson = new Gson();
        try {
            logger.info("handler(): sending " + mqttMessage + " from MQTT to WebSocket...");
            webSocketService.sendMessage("/user/topic/greetings", gson.toJson(mqttMessage));
        } catch (Exception e) {
            logger.info("handler(): error on sending msg from MQTT to WebSocket...");
            e.printStackTrace();
        }
    }

}
