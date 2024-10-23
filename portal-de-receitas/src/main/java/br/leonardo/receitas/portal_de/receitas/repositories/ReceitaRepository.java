package br.leonardo.receitas.portal_de.receitas.repositories;

import br.leonardo.receitas.portal_de.receitas.entidades.Receita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
}
