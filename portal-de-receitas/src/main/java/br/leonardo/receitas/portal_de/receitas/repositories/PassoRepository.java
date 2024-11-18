//Feito Por: 
// Leonardo De Castro Tonon Ra: 10426930
// MATHEUS CALEIRO PINHEIRO RA: 10418688
// JOAO PEDRO FERNANDES MILHOMENS RA: 10417578

package br.leonardo.receitas.portal_de.receitas.repositories;

import br.leonardo.receitas.portal_de.receitas.entidades.Passo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PassoRepository extends JpaRepository<Passo, Long> {
    List<Passo> findByReceitaIdOrderByOrdem(Long receitaId);
}
