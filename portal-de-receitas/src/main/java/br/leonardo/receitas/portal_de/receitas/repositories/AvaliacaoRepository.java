package br.leonardo.receitas.portal_de.receitas.repositories;

// Importa as classes necessárias para o repositório de Avaliacao
import br.leonardo.receitas.portal_de.receitas.entidades.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

// Interface que estende JpaRepository para operações com Avaliacao
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
}
