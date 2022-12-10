package br.infnet.edu.requerimentoCompensacao.repository;

import br.infnet.edu.requerimentoCompensacao.entity.Dependente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DependenteRepository extends JpaRepository<Dependente,Long> {
}
