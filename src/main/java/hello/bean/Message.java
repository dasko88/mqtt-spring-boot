package hello.bean;

import java.io.Serializable;

/**
 *
 * @author David
 */
public class Message implements Serializable {

    private String destination;
    private String content;
    private String user;

    public Message() {
    }

    public Message(String destination, String content, String user) {
        this.destination = destination;
        this.content = content;
        this.user = user;
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

    @Override
    public String toString() {
        return "Message{" + "destination=" + destination + ", content=" + content + ", user=" + user + '}';
    }

}
