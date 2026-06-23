package br.com.infnet.Almoxarifado.model;

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
public class Endereco {
    private String cep;
    private Integer numero;
    private String rua;
    private String bairro;
    private String complemento;
    private String cidade;
    private String uf;
}
