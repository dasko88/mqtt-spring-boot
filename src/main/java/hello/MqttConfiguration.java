package hello;

import hello.handler.WebSocketHandler;
import hello.transformer.MessageTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;

/**
 *
 * @author David
 */
@Configuration
public class MqttConfiguration {

    @Autowired
    WebSocketHandler webSocketHandler;

    @Autowired
    MessageTransformer messageTransformer;

    /**
     * Definisce il broker
     *
     * @return
     */
    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setServerURIs("tcp://localhost:1883");
        return factory;
    }

    /**
     * Definisce il subscriber
     *
     * @return
     */
    @Bean
    public MessageProducerSupport mqttInbound() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter("spring-dsl-client",
                mqttClientFactory(), "test/#");
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        return adapter;
    }

    /**
     * Definisce il flusso da eseguire per il subscriber
     *
     * @return
     */
    @Bean
    public IntegrationFlow mqttInFlow() {
        return IntegrationFlows.from(mqttInbound())
                .transform(messageTransformer)
                .handle(webSocketHandler)
                .get();
    }
}
