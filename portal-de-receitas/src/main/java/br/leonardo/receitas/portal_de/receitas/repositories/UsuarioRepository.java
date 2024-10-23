package br.leonardo.receitas.portal_de.receitas.repositories;

import br.leonardo.receitas.portal_de.receitas.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
