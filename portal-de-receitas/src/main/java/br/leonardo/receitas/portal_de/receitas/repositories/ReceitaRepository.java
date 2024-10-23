package br.leonardo.receitas.portal.repositories;

import br.leonardo.receitas.portal.Receita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
}
