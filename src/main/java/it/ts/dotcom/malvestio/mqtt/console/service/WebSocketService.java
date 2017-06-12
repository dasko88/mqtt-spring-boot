package it.ts.dotcom.malvestio.mqtt.console.service;

import it.ts.dotcom.malvestio.mqtt.console.bean.MqttMessage;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

/**
 *
 * @author David http://assets.spring.io/wp/WebSocketBlogPost.html
 * https://github.com/codependent/spring-websocket-sample
 */
@Service
public class WebSocketService {

    private static final Logger logger = Logger.getLogger(WebSocketService.class);

    private final MessageSendingOperations<String> messagingTemplate;

    @Autowired
    public WebSocketService(MessageSendingOperations<String> messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendMessage(String topic, String content) {
        logger.info("sendMessage()....");

        Map<String, Object> map = new HashMap<>();
        map.put(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON);

        MqttMessage msg = new MqttMessage(topic, content, "http-request", new Date());

        this.messagingTemplate.convertAndSend(msg.getDestination(), msg);
    }
}
