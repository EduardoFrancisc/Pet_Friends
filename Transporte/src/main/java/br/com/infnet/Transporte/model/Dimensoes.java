package br.com.infnet.Transporte.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dimensoes {
    private Double altura;
    private Double largura;
    private Double profundidade;
    private Double peso;
}
