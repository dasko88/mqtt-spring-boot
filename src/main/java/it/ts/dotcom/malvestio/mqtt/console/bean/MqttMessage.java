package it.ts.dotcom.malvestio.mqtt.console.bean;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author David
 */
public class MqttMessage implements Serializable {

    private String destination;
    private String content;
    private String user;
    private Date now;

    public MqttMessage() {
    }

    public MqttMessage(String destination, String content, String user, Date now) {
        this.destination = destination;
        this.content = content;
        this.user = user;
        this.now = now;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getNow() {
        return now;
    }

    public void setNow(Date now) {
        this.now = now;
    }

    @Override
    public String toString() {
        return "MqttMessage{" + "destination=" + destination + ", content=" + content + ", user=" + user + ", now=" + now + '}';
    }

}
