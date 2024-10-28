package br.leonardo.receitas.portal_de.receitas.repositories;

import br.leonardo.receitas.portal_de.receitas.entidades.Ingrediente; // Importa a entidade Ingrediente
import org.springframework.data.jpa.repository.JpaRepository; // Importa a interface JpaRepository

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> { // Interface para o repositório da entidade Ingrediente
}
