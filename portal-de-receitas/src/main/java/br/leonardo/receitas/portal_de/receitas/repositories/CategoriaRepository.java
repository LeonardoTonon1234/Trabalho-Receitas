package br.leonardo.receitas.portal.repositories;

import br.leonardo.receitas.portal.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
