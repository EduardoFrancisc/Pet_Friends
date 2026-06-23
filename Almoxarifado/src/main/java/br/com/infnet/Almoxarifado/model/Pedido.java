package br.com.infnet.Almoxarifado.model;

import br.com.infnet.Almoxarifado.enums.PedidoStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String produto;
    private Integer quantidade;
    @Enumerated(EnumType.STRING)
    private PedidoStatus status;
    private Endereco endereco;
    private List<ItemPedido> itens;
}
