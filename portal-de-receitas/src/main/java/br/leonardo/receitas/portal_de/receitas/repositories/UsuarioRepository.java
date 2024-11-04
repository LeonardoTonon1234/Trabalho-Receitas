//Feito Por: 
// Leonardo De Castro Tonon Ra: 10426930
//MATHEUS CALEIRO PINHEIRO RA: 10418688
//JOAO PEDRO FERNANDES MILHOMENS RA: 10417578

package br.leonardo.receitas.portal_de.receitas.repositories;

import br.leonardo.receitas.portal_de.receitas.entidades.Usuario; // Importa a entidade Usuario
import org.springframework.data.jpa.repository.JpaRepository; // Importa a interface JpaRepository

import java.util.Optional; // Importa a classe Optional

public interface UsuarioRepository extends JpaRepository<Usuario, Long> { // Interface para o repositório da entidade Usuario
    Optional<Usuario> findByEmail(String email); // Método para buscar um usuário pelo email
}
