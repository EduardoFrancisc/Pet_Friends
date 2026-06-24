package br.com.infnet.Pedidos.events;

import br.com.infnet.Pedidos.dto.DimensoesDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PedidoCriadoEventTransporte implements Serializable {
    private Integer pedidoId;
    private Integer enderecoId;
    private DimensoesDTO dimensoes;
}
