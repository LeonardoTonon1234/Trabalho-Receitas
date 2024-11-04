//Feito Por: 
// Leonardo De Castro Tonon Ra: 10426930
//MATHEUS CALEIRO PINHEIRO RA: 10418688
//JOAO PEDRO FERNANDES MILHOMENS RA: 10417578

package br.leonardo.receitas.portal_de.receitas.repositories;

import br.leonardo.receitas.portal_de.receitas.entidades.Passo; // Importa a entidade Passo
import org.springframework.data.jpa.repository.JpaRepository; // Importa a interface JpaRepository

public interface PassoRepository extends JpaRepository<Passo, Long> { // Interface para o repositório da entidade Passo
}
