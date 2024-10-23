package br.leonardo.receitas.portal.repositories;

import br.leonardo.receitas.portal.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}
