package br.infnet.edu.requerimentoCompensacao.service;

import br.infnet.edu.requerimentoCompensacao.Message.Message;
import br.infnet.edu.requerimentoCompensacao.controller.dto.input.AtualizaDto;
import br.infnet.edu.requerimentoCompensacao.controller.dto.input.RequerimentoPensaoInputDto;
import br.infnet.edu.requerimentoCompensacao.enums.EnumSituacao;
import br.infnet.edu.requerimentoCompensacao.entity.Requerimento;
import br.infnet.edu.requerimentoCompensacao.repository.RequerimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RequerimentoService {
    @Autowired
    private RequerimentoRepository requerimentoRepository;

    public Requerimento salvar(Requerimento requerimento) {
        return requerimentoRepository.save(requerimento);
    }

    public void cancelar(Long identificador) {
        Requerimento requerimento = requerimentoRepository.getById(identificador);
        requerimentoRepository.delete(requerimento);
    }

    public Message atualizar(AtualizaDto atualizaDto) {
        Message result;

        long situacao = atualizaDto.getSituacao();
        Requerimento req = requerimentoRepository.getById(atualizaDto.getIdentificador());

        if (EnumSituacao.getSituacao(atualizaDto.getSituacao()).equals(EnumSituacao.DEFERIDO)) {
            req.setSituacao("1");
            requerimentoRepository.save(req);
            result = new Message("Requerimento de pensão deferido!");

        } else if (EnumSituacao.getSituacao(atualizaDto.getSituacao()).equals(EnumSituacao.INDEFERIDO)) {
            req.setSituacao("2");
            requerimentoRepository.save(req);
            result = new Message("Requerimento de pensão indeferido!");

        } else {
            result = new Message("Requerimento de pensão não atualizado!");
        }
        return result;
    }

    public List<Requerimento> consultar(RequerimentoPensaoInputDto requerimento) {
        Long solicitante = requerimento.getSolicitante();
        Long destinatario = requerimento.getDestinatario();
        String situacao = requerimento.getSituacao();

        return requerimentoRepository.listarRequerimentos(solicitante, destinatario, situacao);

    }


}
