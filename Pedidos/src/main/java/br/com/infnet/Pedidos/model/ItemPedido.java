package br.com.infnet.Pedidos.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedido {
    private Integer productId;
    private Integer quantidade;
}
