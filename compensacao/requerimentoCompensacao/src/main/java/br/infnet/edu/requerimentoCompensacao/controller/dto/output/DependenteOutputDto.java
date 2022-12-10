package br.infnet.edu.requerimentoCompensacao.controller.dto.output;

import br.infnet.edu.requerimentoCompensacao.entity.Dependente;

public class DependenteOutputDto {
    String nome;
    String cpf;
    long codVinculo;
    long codEstadoCivil;


    public DependenteOutputDto(){
    }

    public DependenteOutputDto(Dependente dependente){
        this.nome = dependente.getNome();
        this.cpf = dependente.getCpf();
        this.codVinculo = dependente.getVinculo();
        this.codEstadoCivil = dependente.getEstadoCivil();
    }

}
