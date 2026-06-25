package br.com.infnet.Transporte.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_TRANSPORTE = "pedidos.transporte";
    public static final String EXCHANGE_TRANSPORTE = "pedidos.transporte.fanout";

    @Bean
    public Queue transporteQueue() {
        return new Queue(QUEUE_TRANSPORTE, true);
    }

    @Bean
    public FanoutExchange transporteExchange() {
        return new FanoutExchange(EXCHANGE_TRANSPORTE);
    }

    @Bean
    public Binding transporteBinding(Queue transporteQueue, FanoutExchange transporteExchange) {
        return BindingBuilder.bind(transporteQueue).to(transporteExchange);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
