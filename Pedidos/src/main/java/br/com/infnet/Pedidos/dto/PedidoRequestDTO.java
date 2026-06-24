package br.com.infnet.Pedidos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoRequestDTO{
    private String nomeCliente;
    private EnderecoRequestDTO endereco;
    private List<ItemPedidoRequestDTO> itens;
    private DimensoesDTO dimensoes;
}
