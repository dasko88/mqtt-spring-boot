package it.ts.dotcom.malvestio.mqtt.console.configuration;

import it.ts.dotcom.malvestio.mqtt.console.configuration.properties.MqttProperties;
import it.ts.dotcom.malvestio.mqtt.console.handler.WebSocketHandler;
import it.ts.dotcom.malvestio.mqtt.console.transformer.MessageTransformer;
import org.eclipse.paho.client.mqttv3.persist.MqttDefaultFilePersistence;
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

    @Autowired
    MqttProperties mqttProperties;

    /**
     * Definisce il broker
     *
     * @return
     */
    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setServerURIs("tcp://" + mqttProperties.getUrl() + ":" + mqttProperties.getPort());
        //http://www.hivemq.com/blog/mqtt-essentials-part-7-persistent-session-queuing-messages
        factory.setCleanSession(Boolean.FALSE);

        if (mqttProperties.getUseCredential()) {
            factory.setUserName(mqttProperties.getUsername());
            factory.setPassword(mqttProperties.getPassword());
        }

        String tmpDir = System.getProperty("java.io.tmpdir");
        MqttDefaultFilePersistence dataStore = new MqttDefaultFilePersistence(tmpDir);
        factory.setPersistence(dataStore);

        return factory;
    }

    /**
     * Definisce il subscriber
     *
     * @return
     */
    @Bean
    public MessageProducerSupport mqttInbound() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(mqttProperties.getClientSubId(),
                mqttClientFactory(), mqttProperties.getClientSubTopic());

        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        //http://www.hivemq.com/blog/mqtt-essentials-part-6-mqtt-quality-of-service-levels
        adapter.setQos(Integer.parseInt(mqttProperties.getQos()));
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
