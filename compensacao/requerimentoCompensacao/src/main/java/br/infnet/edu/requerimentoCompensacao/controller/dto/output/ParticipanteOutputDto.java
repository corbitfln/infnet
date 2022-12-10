package br.infnet.edu.requerimentoCompensacao.controller.dto.output;

import br.infnet.edu.requerimentoCompensacao.entity.Participante;
import lombok.Data;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ParticipanteOutputDto {
    private String codigo;
    private String nome;

    ParticipanteOutputDto() {
    }

    ParticipanteOutputDto(Participante participante){
        this.codigo = participante.getCodigo();
        this.nome = participante.getNome();
    }

    public static List<ParticipanteOutputDto> listDeParticipantes(List<Participante> participantes) {
        return participantes.stream()
                .map(ParticipanteOutputDto::new)
                .collect(Collectors.toList());
    }

    public ParticipanteOutputDto getParticipante(Participante participante){
        ParticipanteOutputDto part = new ParticipanteOutputDto();
        part.nome = participante.getNome();
        part.codigo = participante.getCodigo();
        return part;
    }

}