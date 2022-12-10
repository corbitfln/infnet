package br.infnet.edu.requerimentoCompensacao.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Data
@Entity
@Table(name="requerimento")
public class Requerimento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="identificador")
    private Long identificador;
    private Long solicitante;
    private Long destinatario;
    private String cpfBeneficiario;
    private String dataInicioBeneficioPensao;
    private String situacao;
    private Instant dataCriacao = Instant.now();
    private double rendaMensalInicial;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "requerimento_identificador")
    private List<Dependente> dependentes;

    public Requerimento() {
        super();
    }
}
