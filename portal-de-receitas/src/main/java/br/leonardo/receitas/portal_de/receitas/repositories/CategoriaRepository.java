package br.leonardo.receitas.portal_de.receitas.repositories;

import br.leonardo.receitas.portal_de.receitas.entidades.Categoria;
import org.springframework.data.jpa.repository.JpaRepository; // Importa a interface JpaRepository

public interface CategoriaRepository extends JpaRepository<Categoria, Long> { // Interface para o repositório da entidade Categoria
}
