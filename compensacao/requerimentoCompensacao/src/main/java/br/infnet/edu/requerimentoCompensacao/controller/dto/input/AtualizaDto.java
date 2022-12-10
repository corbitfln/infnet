package br.infnet.edu.requerimentoCompensacao.controller.dto.input;

import lombok.Data;

@Data
public class AtualizaDto {

    private long identificador;
    private long situacao;

    public AtualizaDto(){
    }

    public AtualizaDto build(){
        AtualizaDto atualizaDto = new AtualizaDto();
        atualizaDto.setIdentificador(this.identificador);
        atualizaDto.setSituacao(this.situacao);
        return atualizaDto;
    }


}
