package br.leonardo.receitas.portal_de.receitas.repositories;

import br.leonardo.receitas.portal_de.receitas.entidades.Comentario;
import org.springframework.data.jpa.repository.JpaRepository; // Importa a interface JpaRepository

public interface ComentarioRepository extends JpaRepository<Comentario, Long> { // Interface para o repositório da entidade Comentario
}
