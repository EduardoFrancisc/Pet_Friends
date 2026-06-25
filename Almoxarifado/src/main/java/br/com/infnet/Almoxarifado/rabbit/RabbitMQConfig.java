package br.com.infnet.Almoxarifado.rabbit;

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

    public static final String QUEUE_ALMOXARIFADO = "pedidos.almoxarifado";
    public static final String EXCHANGE_ALMOXARIFADO = "pedidos.almoxarifado.fanout";

    @Bean
    public Queue almoxarifadoQueue() {
        return new Queue(QUEUE_ALMOXARIFADO, true);
    }

    @Bean
    public FanoutExchange almoxarifadoExchange() {
        return new FanoutExchange(EXCHANGE_ALMOXARIFADO);
    }

    @Bean
    public Binding almoxarifadoBinding(Queue almoxarifadoQueue, FanoutExchange almoxarifadoExchange) {
        return BindingBuilder.bind(almoxarifadoQueue).to(almoxarifadoExchange);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
