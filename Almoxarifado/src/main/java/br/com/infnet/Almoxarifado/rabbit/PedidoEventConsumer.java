package br.com.infnet.Almoxarifado.rabbit;

import br.com.infnet.Almoxarifado.dto.PedidoCriadoEventAlmoxarifado;
import br.com.infnet.Almoxarifado.enums.PedidoStatus;
import br.com.infnet.Almoxarifado.model.Endereco;
import br.com.infnet.Almoxarifado.model.ItemPedido;
import br.com.infnet.Almoxarifado.model.Pedido;
import br.com.infnet.Almoxarifado.repository.AlmoxarifadoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoEventConsumer {

    private final AlmoxarifadoRepository almoxarifadoRepository;

    public PedidoEventConsumer(AlmoxarifadoRepository pedidoRepository) {
        this.almoxarifadoRepository = pedidoRepository;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_ALMOXARIFADO)
    public void receberPedidoCriado(PedidoCriadoEventAlmoxarifado event) {
        Endereco endereco = Endereco.builder()
                .cep(event.getEndereco().getCep())
                .numero(event.getEndereco().getNumero())
                .rua(event.getEndereco().getRua())
                .bairro(event.getEndereco().getBairro())
                .complemento(event.getEndereco().getComplemento())
                .cidade(event.getEndereco().getCidade())
                .uf(event.getEndereco().getUf())
                .build();

        List<ItemPedido> itens = event.getItens().stream().map(item -> new ItemPedido(
                item.getProductId(),
                item.getQuantidade()
        )).collect(Collectors.toList());

        Pedido pedido = Pedido.builder()
                .cliente(event.getNomeCliente())
                .status(PedidoStatus.CRIADO)
                .endereco(endereco)
                .itens(itens)
                .build();

        almoxarifadoRepository.save(pedido);
        System.out.println("Pedido de "+event.getNomeCliente()+" criado processado no Almoxarifado!");
    }
}
