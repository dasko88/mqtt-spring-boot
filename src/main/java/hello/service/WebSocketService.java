package hello.service;

import hello.bean.Message;
import java.util.HashMap;
import java.util.Map;
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

    private final MessageSendingOperations<String> messagingTemplate;

    @Autowired
    public WebSocketService(MessageSendingOperations<String> messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendMessage(String topic, String content) {
        System.out.println("sendMessage()....");

        Map<String, Object> map = new HashMap<>();
        map.put(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON);

        Message msg = new Message(topic, content, "http-request");

        this.messagingTemplate.convertAndSend(msg.getDestination(), msg);
    }
}
