package br.com.infnet.Pedidos.rabbit;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_ALMOXARIFADO = "pedidos.almoxarifado.fanout";
    public static final String EXCHANGE_TRANSPORTE = "pedidos.transporte.fanout";

    @Bean
    public FanoutExchange almoxarifadoExchange() {
        return new FanoutExchange(EXCHANGE_ALMOXARIFADO);
    }

    @Bean
    public FanoutExchange transporteExchange() {
        return new FanoutExchange(EXCHANGE_TRANSPORTE);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
