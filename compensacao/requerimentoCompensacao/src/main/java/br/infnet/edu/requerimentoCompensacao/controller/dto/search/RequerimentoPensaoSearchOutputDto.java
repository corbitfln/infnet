package br.infnet.edu.requerimentoCompensacao.controller.dto.search;

import br.infnet.edu.requerimentoCompensacao.entity.Requerimento;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class RequerimentoPensaoSearchOutputDto {

    private Long identificador;
    private Long solicitante;
    private Long destinatario;
    private String cpfBeneficiario;
    private String dataInicioBeneficioPensao;
    private String situacao;
    private Instant dataCriacao;
    private double rendaMensalInicial;


    public RequerimentoPensaoSearchOutputDto() {
    }

    public RequerimentoPensaoSearchOutputDto(Requerimento requerimento) {
        this.identificador = requerimento.getIdentificador();
        this.cpfBeneficiario = requerimento.getCpfBeneficiario();
        this.solicitante = requerimento.getSolicitante();
        this.destinatario = requerimento.getDestinatario();
        this.dataInicioBeneficioPensao = requerimento.getDataInicioBeneficioPensao();
        this.situacao = requerimento.getSituacao();
        this.dataCriacao = requerimento.getDataCriacao();
        this.rendaMensalInicial = requerimento.getRendaMensalInicial();
    }

    public static List<RequerimentoPensaoSearchOutputDto> listaDeRequerimentos(List<Requerimento> requerimentos) {
        return requerimentos.stream()
                .map(RequerimentoPensaoSearchOutputDto::new)
                .collect(Collectors.toList());
    }

    public static Page<RequerimentoPensaoSearchOutputDto> fromTopicPage(Page<Requerimento> requerimentos) {
        return requerimentos.map(RequerimentoPensaoSearchOutputDto::new);
    }

}
