package br.com.infnet.Almoxarifado.model;

import lombok.Data;

@Data
public class Endereco {
    private Integer id;
    private String cep;
    private Integer numero;
    private String rua;
    private String bairro;
    private String complemento;
    private String cidade;
    private String uf;
}
