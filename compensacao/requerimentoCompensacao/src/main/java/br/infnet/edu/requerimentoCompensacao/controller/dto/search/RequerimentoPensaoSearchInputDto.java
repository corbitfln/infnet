package br.infnet.edu.requerimentoCompensacao.controller.dto.search;

import br.infnet.edu.requerimentoCompensacao.entity.Requerimento;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;

@Data
public class RequerimentoPensaoSearchInputDto {
    private Long solicitante;
    private Long destinatario;
    private String situacao;

    public RequerimentoPensaoSearchInputDto(Long idSolicitante, Long idDestinatario, String situacao){
        if (notIsNull(idSolicitante)) {
            setSolicitante(idSolicitante);
        }
        if (notIsNull(idDestinatario)) {
            setDestinatario(idDestinatario);
        }
        if (notIsNull(situacao)) {
            setSituacao(situacao);
        }
    }

    public Specification<Requerimento> build(){
        return (root, criteriaQuery, criteriaBuilder) -> {

            ArrayList<Predicate> predicates = new ArrayList<>();

            if(solicitante != null) {
                predicates.add(criteriaBuilder.equal(root.get("idSolicitante"), solicitante));
            }

            if(destinatario != null ) {
                predicates.add(criteriaBuilder.equal(root.get("idDestinatario"), destinatario));
            }

            if(situacao != null ) {
                predicates.add(criteriaBuilder.equal(root.get("situacao"), situacao));
            }


            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

    }

    private boolean notIsNull(Long campo) {
        if (campo != null) {
            return true;
        }
        return false;
    }

    private boolean notIsNull(String campo) {
        if (campo != null) {
            return true;
        }
        return false;
    }



}
