package br.com.infnet.Pedidos.events;

import br.com.infnet.Pedidos.dto.EnderecoRequestDTO;
import br.com.infnet.Pedidos.dto.ItemPedidoRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoCriadoEventAlmoxarifado implements Serializable {
    private String nomeCliente;
    private EnderecoRequestDTO endereco;
    private List<ItemPedidoRequestDTO> itens;
}
