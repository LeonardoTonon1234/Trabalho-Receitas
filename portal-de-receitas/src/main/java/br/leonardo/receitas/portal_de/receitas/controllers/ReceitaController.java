package br.leonardo.receitas.portal_de.receitas.controllers;

import br.leonardo.receitas.portal_de.receitas.entidades.Receita; // Importa a entidade Receita
import br.leonardo.receitas.portal_de.receitas.repositories.ReceitaRepository; // Importa o repositório de Receita
import br.leonardo.receitas.portal_de.receitas.exceptions.ResourceNotFoundException; // Importa a exceção personalizada
import org.springframework.beans.factory.annotation.Autowired; // Importa a anotação para injeção de dependência
import org.springframework.http.HttpStatus; // Importa a classe HttpStatus
import org.springframework.http.ResponseEntity; // Importa a classe ResponseEntity
import org.springframework.web.bind.annotation.*; // Importa as anotações para o controlador

import java.util.List; // Importa a classe List

@RestController // Indica que esta classe é um controlador REST
@RequestMapping("/api/receitas") // Mapeia requisições para /api/receitas
public class ReceitaController {

    @Autowired // Injeção de dependência do repositório de receitas
    private ReceitaRepository receitaRepository;

    @GetMapping // Mapeia requisições GET para obter todas as receitas
    public List<Receita> getAllReceitas() {
        return receitaRepository.findAll(); // Retorna todas as receitas do banco de dados
    }

    @PostMapping // Mapeia requisições POST para criar uma nova receita
    public ResponseEntity<Receita> createReceita(@RequestBody Receita receita) {
        Receita savedReceita = receitaRepository.save(receita); // Salva a nova receita no banco de dados
        return new ResponseEntity<>(savedReceita, HttpStatus.CREATED); // Retorna a receita salva com status 201
    }

    @PutMapping("/{id}") // Mapeia requisições PUT para atualizar uma receita existente
    public ResponseEntity<Receita> updateReceita(@PathVariable Long id, @RequestBody Receita receita) {
        Receita existingReceita = receitaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receita não encontrada")); // Lança exceção se a receita não for encontrada
        existingReceita.setNome(receita.getNome()); // Atualiza o nome da receita
        existingReceita.setDescricao(receita.getDescricao()); // Atualiza a descrição da receita
        return ResponseEntity.ok(receitaRepository.save(existingReceita)); // Retorna a receita atualizada
    }

    @DeleteMapping("/{id}") // Mapeia requisições DELETE para excluir uma receita
    public ResponseEntity<Void> deleteReceita(@PathVariable Long id) {
        if (!receitaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Receita não encontrada"); // Lança exceção se a receita não existir
        }
        receitaRepository.deleteById(id); // Exclui a receita do banco de dados
        return ResponseEntity.noContent().build(); // Retorna resposta 204 (sem conteúdo)
    }

    @GetMapping("/por-ingrediente/{ingredienteId}") // Mapeia requisições GET para buscar receitas por ingrediente
    public List<Receita> getReceitasByIngrediente(@PathVariable Long ingredienteId) {
        return receitaRepository.findByIngredientes_Id(ingredienteId); // Retorna receitas que contêm o ingrediente especificado
    }

    @GetMapping("/por-categoria/{categoriaId}") // Mapeia requisições GET para buscar receitas por categoria
    public List<Receita> getReceitasByCategoria(@PathVariable Long categoriaId) {
        return receitaRepository.findByCategoria_Id(categoriaId); // Retorna receitas que pertencem à categoria especificada
    }
}
