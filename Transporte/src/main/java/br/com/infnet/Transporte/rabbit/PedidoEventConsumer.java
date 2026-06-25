package br.com.infnet.Transporte.rabbit;

import br.com.infnet.Transporte.dto.PedidoCriadoEventTransporte;
import br.com.infnet.Transporte.enums.StatusTransporte;
import br.com.infnet.Transporte.model.Dimensoes;
import br.com.infnet.Transporte.model.Entrega;
import br.com.infnet.Transporte.repository.EntregaRepositoy;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class PedidoEventConsumer {

    private final EntregaRepositoy entregaRepositoy;

    public PedidoEventConsumer(EntregaRepositoy entregaRepositoy) {
        this.entregaRepositoy = entregaRepositoy;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_TRANSPORTE)
    public void receberPedidoCriado(PedidoCriadoEventTransporte event) {
        Dimensoes dimensoes = Dimensoes.builder()
                .altura(event.getDimensoes().getAltura())
                .largura(event.getDimensoes().getLargura())
                .profundidade(event.getDimensoes().getProfundidade())
                .peso(event.getDimensoes().getPeso())
                .build();

        Entrega entrega = Entrega.builder()
                .pedidoId(event.getPedidoId())
                .enderecoId(event.getEnderecoId())
                .dimensoes(dimensoes)
                .status(StatusTransporte.AGUARDANDO_COLETA)
                .dataDespacho(LocalDateTime.now())
                .build();

        entregaRepositoy.save(entrega);
        System.out.println("Pedido criado"+event.getPedidoId()+"processado no Transporte com sucesso!");
    }
}
