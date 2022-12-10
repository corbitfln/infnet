package br.infnet.edu.requerimentoCompensacao.repository;

import br.infnet.edu.requerimentoCompensacao.entity.Requerimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RequerimentoRepository extends JpaRepository<Requerimento, Long>, JpaSpecificationExecutor<Requerimento>{

    @Query("select r from Requerimento r")
    List<Requerimento> list();

    @Query("select count(r.cpfBeneficiario) from Requerimento r " +
            "where r.cpfBeneficiario = :cpfBeneficiario ")
    int countCpfBeneficiario(String cpfBeneficiario);

    @Query("select count(r.solicitante) from Requerimento r " +
            "where r.solicitante = :solicitante ")
    int countIdSolicitante(Long solicitante);

    @Query("select count(r.destinatario) from Requerimento r " +
            "where r.destinatario = :destinatario ")
    int countIdDestinatario(Long destinatario);

    @Query("select r from Requerimento r " +
            "where r.solicitante = :solicitante " +
            "and r.destinatario = :destinatario " +
            "and r.situacao = :situacao ")
    List<Requerimento> listarRequerimentos(@Param("solicitante") Long solicitante,
                                           @Param("destinatario") Long destinatario,
                                           @Param("situacao") String situacao);
}
