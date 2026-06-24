package br.com.infnet.Almoxarifado.model;

import br.com.infnet.Almoxarifado.enums.PedidoStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

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
    private String cliente;
    @Enumerated(EnumType.STRING)
    private PedidoStatus status;
    @Embedded
    private Endereco endereco;
    @ElementCollection
    private List<ItemPedido> itens;
}
