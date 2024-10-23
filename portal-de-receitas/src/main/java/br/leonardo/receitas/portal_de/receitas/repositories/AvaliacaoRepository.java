package br.leonardo.receitas.portal_de.receitas.repositories;

import br.leonardo.receitas.portal_de.receitas.entidades.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
}
