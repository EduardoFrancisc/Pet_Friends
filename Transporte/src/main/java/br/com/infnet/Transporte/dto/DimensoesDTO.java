package br.com.infnet.Transporte.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DimensoesDTO {
    private Double altura;
    private Double largura;
    private Double profundidade;
    private Double peso;
}
