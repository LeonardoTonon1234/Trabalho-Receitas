package br.leonardo.receitas.portal.repositories;

import br.leonardo.receitas.portal.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
