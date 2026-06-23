package br.com.infnet.Transporte.model;

import br.com.infnet.Transporte.enums.StatusTransporte;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Entrega {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer pedidoId;
    private Integer enderecoId;
    private Dimensoes dimensoes;
    @Enumerated(EnumType.STRING)
    private StatusTransporte status;
    private LocalDateTime dataDespacho;
    private LocalDateTime dataConclusao;
}
