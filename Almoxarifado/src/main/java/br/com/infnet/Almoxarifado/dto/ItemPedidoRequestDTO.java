package br.com.infnet.Almoxarifado.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemPedidoRequestDTO {
    private Integer productId;
    private Integer quantidade;
}
