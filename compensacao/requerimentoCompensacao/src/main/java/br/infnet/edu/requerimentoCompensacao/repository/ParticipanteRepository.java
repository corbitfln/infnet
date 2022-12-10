package br.infnet.edu.requerimentoCompensacao.repository;

import br.infnet.edu.requerimentoCompensacao.entity.Participante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipanteRepository extends JpaRepository<Participante,Long> {
}
