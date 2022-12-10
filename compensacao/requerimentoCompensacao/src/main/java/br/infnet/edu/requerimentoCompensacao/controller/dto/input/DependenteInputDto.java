package br.infnet.edu.requerimentoCompensacao.controller.dto.input;

import lombok.Data;

@Data
public class DependenteInputDto {
    String nome;
    String cpf;
    long vinculo;
    long estadoCivil;

    public DependenteInputDto(){
    }
}
