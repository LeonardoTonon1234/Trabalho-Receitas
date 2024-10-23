package br.leonardo.receitas.portal_de.receitas.repositories;

import br.leonardo.receitas.portal_de.receitas.entidades.Passo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassoRepository extends JpaRepository<Passo, Long> {
}
