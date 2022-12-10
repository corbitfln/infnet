package br.infnet.edu.requerimentoCompensacao.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "participante")
public class Participante  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long identificador;
    private String codigo;
    private String nome;

    Participante() {
        super();
    }
}
