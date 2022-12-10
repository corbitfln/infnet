package br.infnet.edu.requerimentoCompensacao.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name="dependente")
public class Dependente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long identificador;
    private String cpf;
    private String nome;
    private Long estadoCivil;
    private Long vinculo;

    public Dependente(){
        super();
    }
}

