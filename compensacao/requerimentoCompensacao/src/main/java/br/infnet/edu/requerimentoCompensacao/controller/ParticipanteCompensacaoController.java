package br.infnet.edu.requerimentoCompensacao.controller;


import br.infnet.edu.requerimentoCompensacao.controller.dto.output.ParticipanteOutputDto;
import br.infnet.edu.requerimentoCompensacao.entity.Participante;
import br.infnet.edu.requerimentoCompensacao.repository.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/participantes-compensacao")
public class ParticipanteCompensacaoController {
    @Autowired
    private ParticipanteRepository participanteRepository;

    @Cacheable("participantesData")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ParticipanteOutputDto> consultarParticipantes() {
        List<Participante> participantes = this.participanteRepository.findAll();
        return ParticipanteOutputDto.listDeParticipantes(participantes);
    }


}
