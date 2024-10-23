package br.leonardo.receitas.portal_de.receitas.repositories;

import br.leonardo.receitas.portal_de.receitas.entidades.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
