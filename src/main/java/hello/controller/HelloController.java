package hello.controller;

import hello.bean.User;
import hello.component.mqtt.MqttPublishClient;
import hello.model.mapper.UserMapper;
import hello.service.WebSocketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "esempi", description = "esempi generici")
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
    @RequestMapping(value = "/sendToSTOMP", method = RequestMethod.GET)
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
    @RequestMapping(value = "/publishToMQTT", method = RequestMethod.GET)
    public String publish(@RequestParam("topic") String topic, @RequestParam("content") String content) {
        System.out.println("publish(): " + topic + " - " + content);

        publishSample.send(topic, content);

        return "publish(): Message sent!";
    }

    @ApiOperation(value = "Esempio di connessione db")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = "application/json")
    public User user(@ApiParam(name = "id", value = "The id of the user", required = true) @PathVariable Integer id) {
        System.out.println("user()");

        User user = userMapper.get(id);

        return user;
    }

    @ApiOperation(value = "Esempio POST")
    @RequestMapping(value = "/users", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public User add(@ApiParam(name = "user", value = "The new user", required = true) @RequestBody User user) {
        System.out.println("user()" + user);
        user.setId(user.getId() * 10);
        return user;
    }

    @RequestMapping(value = "/disconnect", method = RequestMethod.GET)
    public String disconnect() {
        System.out.println("disconnect(): ");

//        int result = subscriber.disconnect();
        int result = 1;

        return result == 1 ? "OK" : "KO";
    }

    @RequestMapping(value = "/isConnected", method = RequestMethod.GET)
    public String isConnected() {
        System.out.println("isConnected(): ");

//        Boolean result = subscriber.isConnected();
        boolean result = true;

        return result ? "OK" : "KO";
    }

}
