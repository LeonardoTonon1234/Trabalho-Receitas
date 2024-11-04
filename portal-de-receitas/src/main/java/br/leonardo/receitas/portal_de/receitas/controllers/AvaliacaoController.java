//Feito Por: 
// Leonardo De Castro Tonon Ra: 10426930
//MATHEUS CALEIRO PINHEIRO RA: 10418688
//JOAO PEDRO FERNANDES MILHOMENS RA: 10417578

package br.leonardo.receitas.portal_de.receitas.controllers;

// Importa as classes necessárias para o funcionamento do controlador
import br.leonardo.receitas.portal_de.receitas.entidades.Avaliacao;
import br.leonardo.receitas.portal_de.receitas.repositories.AvaliacaoRepository;
import br.leonardo.receitas.portal_de.receitas.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Anota a classe como um controlador REST
@RestController
// Define a base URL para as requisições de avaliação
@RequestMapping("/api/avaliacoes")
public class AvaliacaoController {

    // Injeta o repositório de avaliação
    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    // Método para obter todas as avaliações
    @GetMapping
    public List<Avaliacao> getAllAvaliacoes() {
        // Retorna a lista de todas as avaliações do repositório
        return avaliacaoRepository.findAll();
    }

    // Método para criar uma nova avaliação
    @PostMapping
    public ResponseEntity<Avaliacao> createAvaliacao(@RequestBody Avaliacao avaliacao) {
        // Salva a nova avaliação no repositório
        Avaliacao savedAvaliacao = avaliacaoRepository.save(avaliacao);
        // Retorna a avaliação criada com o status de sucesso
        return new ResponseEntity<>(savedAvaliacao, HttpStatus.CREATED);
    }

    // Método para atualizar uma avaliação existente
    @PutMapping("/{id}")
    public ResponseEntity<Avaliacao> updateAvaliacao(@PathVariable Long id, @RequestBody Avaliacao avaliacao) {
        // Busca a avaliação pelo ID, lançando uma exceção se não for encontrada
        Avaliacao existingAvaliacao = avaliacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Avaliacao not found with id: " + id));
        // Atualiza os atributos da avaliação
        existingAvaliacao.setEstrelas(avaliacao.getEstrelas());
        existingAvaliacao.setReceita(avaliacao.getReceita());
        existingAvaliacao.setUsuario(avaliacao.getUsuario());
        // Salva a avaliação atualizada e retorna com o status de sucesso
        Avaliacao updatedAvaliacao = avaliacaoRepository.save(existingAvaliacao);
        return ResponseEntity.ok(updatedAvaliacao);
    }

    // Método para deletar uma avaliação pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvaliacao(@PathVariable Long id) {
        // Verifica se a avaliação existe, lançando uma exceção se não
        if (!avaliacaoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Avaliacao not found with id: " + id);
        }
        // Deleta a avaliação pelo ID
        avaliacaoRepository.deleteById(id);
        // Retorna um status de sucesso sem corpo
        return ResponseEntity.noContent().build();
    }
}
