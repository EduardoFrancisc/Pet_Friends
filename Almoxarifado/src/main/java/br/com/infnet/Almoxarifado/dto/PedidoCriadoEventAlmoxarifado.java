package br.com.infnet.Almoxarifado.dto;

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
