package br.com.infnet.Pedidos.rabbit;

import br.com.infnet.Pedidos.events.PedidoCriadoEventAlmoxarifado;
import br.com.infnet.Pedidos.events.PedidoCriadoEventTransporte;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class PedidoEventProducer {

    private final RabbitTemplate rabbitTemplate;

    public PedidoEventProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarPedidoParaAlmoxarifado(PedidoCriadoEventAlmoxarifado event) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_ALMOXARIFADO, "", event);
    }

    public void enviarPedidoParaTransporte(PedidoCriadoEventTransporte event) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_TRANSPORTE, "", event);
    }
}
