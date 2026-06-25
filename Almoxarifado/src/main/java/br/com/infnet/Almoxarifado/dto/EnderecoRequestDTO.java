package br.com.infnet.Almoxarifado.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnderecoRequestDTO {
    private String cep;
    private Integer numero;
    private String rua;
    private String bairro;
    private String complemento;
    private String cidade;
    private String uf;
}
