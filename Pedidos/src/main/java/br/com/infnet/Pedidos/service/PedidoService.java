package br.com.infnet.Pedidos.service;

import br.com.infnet.Pedidos.dto.DimensoesDTO;
import br.com.infnet.Pedidos.dto.EnderecoRequestDTO;
import br.com.infnet.Pedidos.dto.ItemPedidoRequestDTO;
import br.com.infnet.Pedidos.dto.PedidoRequestDTO;
import br.com.infnet.Pedidos.events.PedidoCriadoEventAlmoxarifado;
import br.com.infnet.Pedidos.events.PedidoCriadoEventTransporte;
import br.com.infnet.Pedidos.model.Endereco;
import br.com.infnet.Pedidos.model.ItemPedido;
import br.com.infnet.Pedidos.model.Pedido;
import br.com.infnet.Pedidos.rabbit.PedidoEventProducer;
import br.com.infnet.Pedidos.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    private final PedidoEventProducer pedidoEventProducer;
    private final PedidoRepository pedidoRepository;

    public PedidoService(
            PedidoEventProducer pedidoEventProducer,
            PedidoRepository pedidoRepository) {
        this.pedidoEventProducer = pedidoEventProducer;
        this.pedidoRepository = pedidoRepository;
    }

    public void enviarAlmoxarifado(Pedido pedido) {
        EnderecoRequestDTO enderecoRequestDTO = new EnderecoRequestDTO(
                pedido.getEndereco().getCep(),
                pedido.getEndereco().getNumero(),
                pedido.getEndereco().getRua(),
                pedido.getEndereco().getBairro(),
                pedido.getEndereco().getComplemento(),
                pedido.getEndereco().getCidade(),
                pedido.getEndereco().getUf()
        );

        List<ItemPedidoRequestDTO> itensDTO = pedido.getItens().stream().map(item -> new ItemPedidoRequestDTO(
                item.getProductId(),
                item.getQuantidade()
        )).collect(Collectors.toList());

        PedidoCriadoEventAlmoxarifado event = PedidoCriadoEventAlmoxarifado
                .builder()
                .nomeCliente(pedido.getCliente())
                .endereco(enderecoRequestDTO)
                .itens(itensDTO)
                .build();

        pedidoEventProducer.enviarPedidoParaAlmoxarifado(event);
    }

    public void enviarTransporte(Pedido pedido, DimensoesDTO dimensoes) {
        PedidoCriadoEventTransporte transporteEvent = PedidoCriadoEventTransporte.builder()
                .pedidoId(pedido.getId())
                .enderecoId(pedido.getId())
                .dimensoes(dimensoes)
                .build();
        
        pedidoEventProducer.enviarPedidoParaTransporte(transporteEvent);
    }

    @Transactional
    public Pedido processarPedido(PedidoRequestDTO dto) {
        Endereco endereco = Endereco.builder()
                .cep(dto.getEndereco().getCep())
                .numero(dto.getEndereco().getNumero())
                .rua(dto.getEndereco().getRua())
                .bairro(dto.getEndereco().getBairro())
                .complemento(dto.getEndereco().getComplemento())
                .cidade(dto.getEndereco().getCidade())
                .uf(dto.getEndereco().getUf())
                .build();

        List<ItemPedido> itens = dto.getItens().stream().map(item -> new ItemPedido(
                item.getProductId(),
                item.getQuantidade()
        )).collect(Collectors.toList());

        Pedido pedido = Pedido.builder()
                .cliente(dto.getNomeCliente())
                .endereco(endereco)
                .itens(itens)
                .build();

        pedido = pedidoRepository.save(pedido);
        System.out.println("Pedido salvo com ID: " + pedido.getId());

        enviarAlmoxarifado(pedido);
        enviarTransporte(pedido, dto.getDimensoes());

        return pedido;
    }
}
