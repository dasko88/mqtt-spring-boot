package hello.component.mqtt;

import com.google.gson.Gson;
import hello.ApplicationContextHolder;
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

    @Override
    public void connectionLost(Throwable thrwbl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void messageArrived(String string, MqttMessage mm) throws Exception {
        System.out.println("subscriber(): Topic:" + string + " | Message: " + new String(mm.getPayload()));

        // non richiedere la classe dal contesto https://stackoverflow.com/questions/19896870/why-is-my-spring-autowired-field-null
        // creare un singleton         
        WebSocketService webSocketService = ApplicationContextHolder.getContext().getBean(WebSocketService.class);
        System.out.println("subscriber(): sending msg from MQTT to WebSocket...");
        try {
            Message msg = new Message(string, new String(mm.getPayload()), "javaCallback");
            Gson gson = new Gson();

            webSocketService.sendMessage("/user/topic/greetings", gson.toJson(msg));
        } catch (Exception e) {
            System.out.println("subscriber(): error on sending msg from MQTT to WebSocket...\n" + e.toString());
        }
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken imdt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
