package br.leonardo.receitas.portal.repositories;

import br.leonardo.receitas.portal.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {
}
