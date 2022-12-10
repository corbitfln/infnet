package br.infnet.edu.requerimentoCompensacao.controller.dto.output;

import br.infnet.edu.requerimentoCompensacao.entity.Requerimento;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class RequerimentoPensaoOutputDto {
    private long identificador;
    private long solicitante;
    private long destinatario;
    private String cpfBeneficiario;
    private String dataInicioBeneficioPensao;
    private String situacao;
    private Instant dataCriacao;
    private double rendaMensalInicial;


    public RequerimentoPensaoOutputDto() {
    }

    public RequerimentoPensaoOutputDto(Requerimento requerimento) {
        this.identificador = requerimento.getIdentificador();
        this.cpfBeneficiario = requerimento.getCpfBeneficiario();
        this.solicitante = requerimento.getSolicitante();
        this.destinatario = requerimento.getDestinatario();
        this.dataInicioBeneficioPensao = requerimento.getDataInicioBeneficioPensao();
        this.situacao = requerimento.getSituacao();
        this.dataCriacao = requerimento.getDataCriacao();
        this.rendaMensalInicial = requerimento.getRendaMensalInicial();
    }

    public static List<RequerimentoPensaoOutputDto> listDeRequerimentos(List<Requerimento> requerimentos) {
        return requerimentos.stream()
                .map(RequerimentoPensaoOutputDto::new)
                .collect(Collectors.toList());
    }

}
