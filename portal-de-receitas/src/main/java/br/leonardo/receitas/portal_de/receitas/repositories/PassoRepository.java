package br.leonardo.receitas.portal.repositories;

import br.leonardo.receitas.portal.Passo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassoRepository extends JpaRepository<Passo, Long> {
}
