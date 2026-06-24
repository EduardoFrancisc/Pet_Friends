package br.com.infnet.Pedidos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoRequestDTO{
    private String cep;
    private Integer numero;
    private String rua;
    private String bairro;
    private String complemento;
    private String cidade;
    private String uf;
}
