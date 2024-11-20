//Feito Por: 
// Leonardo De Castro Tonon Ra: 10426930
// MATHEUS CALEIRO PINHEIRO RA: 10418688
// JOAO PEDRO FERNANDES MILHOMENS RA: 10417578

package br.leonardo.receitas.portal_de.receitas.controllers;

import br.leonardo.receitas.portal_de.receitas.entidades.Receita;
import br.leonardo.receitas.portal_de.receitas.exceptions.ResourceNotFoundException;
import br.leonardo.receitas.portal_de.receitas.repositories.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/receitas")
public class ReceitaController {

    @Autowired
    private ReceitaRepository receitaRepository;

    // Método para verificar se o usuário está logado (simulação básica)
    private boolean isUserLoggedIn() {
        // Substituir essa lógica pelo sistema de autenticação do projeto
        return true; // Simula que o usuário está logado
    }

    // Obter todas as receitas
    @GetMapping
    public List<Receita> getAllReceitas() {
        return receitaRepository.findAll();
    }

    // Obter receita por ID
    @GetMapping("/{id}")
    public ResponseEntity<Receita> getReceitaById(@PathVariable Long id) {
        Receita receita = receitaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receita não encontrada com ID: " + id));
        return ResponseEntity.ok(receita);
    }

    // Criar uma nova receita
    @PostMapping
    public ResponseEntity<Receita> createReceita(@RequestBody Receita receita) {
        if (!isUserLoggedIn()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Retorna erro de não autorizado
        }
        Receita savedReceita = receitaRepository.save(receita);
        return new ResponseEntity<>(savedReceita, HttpStatus.CREATED);
    }

    // Atualizar uma receita existente
    @PutMapping("/{id}")
    public ResponseEntity<Receita> updateReceita(@PathVariable Long id, @RequestBody Receita receitaAtualizada) {
        if (!isUserLoggedIn()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Retorna erro de não autorizado
        }
        Receita receita = receitaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receita não encontrada com ID: " + id));
        receita.setNome(receitaAtualizada.getNome());
        receita.setDescricao(receitaAtualizada.getDescricao());
        receita.setCategoria(receitaAtualizada.getCategoria());
        Receita updatedReceita = receitaRepository.save(receita);
        return ResponseEntity.ok(updatedReceita);
    }

    // Deletar uma receita
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReceita(@PathVariable Long id) {
        if (!isUserLoggedIn()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Retorna erro de não autorizado
        }
        if (!receitaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Receita não encontrada com ID: " + id);
        }
        receitaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Buscar receitas por nome
    @GetMapping("/buscar")
    public List<Receita> buscarReceitasPorNome(@RequestParam String nome) {
        return receitaRepository.findByNomeContainingIgnoreCase(nome);
    }

    // Obter receitas por categoria
    @GetMapping("/por-categoria/{categoriaId}")
    public List<Receita> getReceitasByCategoria(@PathVariable Long categoriaId) {
        return receitaRepository.findByCategoria_Id(categoriaId);
    }

    // Obter detalhes da receita por ID
    @GetMapping("/{id}/detalhes")
    public ResponseEntity<Receita> getReceitaDetalhes(@PathVariable Long id) {
        Receita receita = receitaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receita não encontrada com o ID: " + id));
        return ResponseEntity.ok(receita);
    }
}
