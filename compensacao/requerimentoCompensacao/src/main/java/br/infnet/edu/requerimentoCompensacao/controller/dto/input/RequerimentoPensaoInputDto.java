package br.infnet.edu.requerimentoCompensacao.controller.dto.input;

import br.infnet.edu.requerimentoCompensacao.entity.Dependente;
import br.infnet.edu.requerimentoCompensacao.entity.Requerimento;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class RequerimentoPensaoInputDto{
    private long solicitante;
    private long destinatario;
    private String cpfBeneficiario;
    private String dataInicioBeneficioPensao;
    private String situacao;
    private double rendaMensalInicial;
    private List<DependenteInputDto> dependentes;



    public RequerimentoPensaoInputDto(){
    }

    public Requerimento build() {
        Requerimento requerimento = new Requerimento();
        requerimento.setSolicitante(this.solicitante);
        requerimento.setDestinatario(this.destinatario);
        requerimento.setCpfBeneficiario(this.cpfBeneficiario);
        requerimento.setRendaMensalInicial(this.rendaMensalInicial);
        requerimento.setDataInicioBeneficioPensao(this.dataInicioBeneficioPensao);
        requerimento.setSituacao(this.situacao);
        if (isNotEmpty(dependentes)){requerimento.setDependentes(getDependentesRequerimento());}
        return requerimento;

    }

    private boolean isNotEmpty(List<DependenteInputDto> dependentes) {
        if ((dependentes != null) && (dependentes.size() > 0)) {
            return true;
        }
        return false;
    }

    private List<Dependente> getDependentesRequerimento(){
       List<Dependente> listaDependentes = new ArrayList<Dependente>();
        for(DependenteInputDto dependenteDto : dependentes){
            Dependente dep = new Dependente();
            dep.setVinculo(dependenteDto.getVinculo());
            dep.setEstadoCivil(dependenteDto.getEstadoCivil());
            dep.setCpf(dependenteDto.getCpf());
            dep.setNome(dependenteDto.getNome());
            listaDependentes.add(dep);
        }
        return listaDependentes;
    }

}
