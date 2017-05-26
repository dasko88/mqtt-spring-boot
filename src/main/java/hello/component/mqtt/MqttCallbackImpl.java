package hello.component.mqtt;

import com.google.gson.Gson;
import hello.bean.Message;
import hello.service.WebSocketService;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 *
 * @author david
 */
public class MqttCallbackImpl implements MqttCallback {

    WebSocketService webSocketService;

    public MqttCallbackImpl(WebSocketService webSocketService) {
        this.webSocketService = webSocketService;
    }

    @Override
    public void connectionLost(Throwable thrwbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void messageArrived(String string, MqttMessage mm) throws Exception {
        System.out.println("subscriber(): new message from topic:" + string + " | message: " + new String(mm.getPayload()));

        try {
            Message msg = new Message(string, new String(mm.getPayload()), "javaCallback");
            Gson gson = new Gson();

            System.out.println("subscriber(): sending " + msg + " from MQTT to WebSocket...");
            webSocketService.sendMessage("/user/topic/greetings", gson.toJson(msg));
        } catch (Exception e) {
            System.out.println("subscriber(): error on sending msg from MQTT to WebSocket...");
            e.printStackTrace();
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken imdt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
