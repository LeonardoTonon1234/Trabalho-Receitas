package br.leonardo.receitas.portal_de.receitas.repositories;

import br.leonardo.receitas.portal_de.receitas.entidades.Receita; // Importa a entidade Receita
import org.springframework.data.jpa.repository.JpaRepository; // Importa a interface JpaRepository

import java.util.List; // Importa a classe List

public interface ReceitaRepository extends JpaRepository<Receita, Long> { // Interface para o repositório da entidade Receita
    List<Receita> findByIngredientes_Id(Long ingredienteId); // Método para buscar receitas por ID de ingrediente
    List<Receita> findByCategoria_Id(Long categoriaId); // Método para buscar receitas por ID de categoria
}
