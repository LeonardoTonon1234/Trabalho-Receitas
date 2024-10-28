package br.leonardo.receitas.portal_de.receitas.controllers;

import br.leonardo.receitas.portal_de.receitas.entidades.Ingrediente; // Importa a entidade Ingrediente
import br.leonardo.receitas.portal_de.receitas.repositories.IngredienteRepository; // Importa o repositório de Ingrediente
import br.leonardo.receitas.portal_de.receitas.exceptions.ResourceNotFoundException; // Importa a exceção personalizada
import org.springframework.beans.factory.annotation.Autowired; // Importa a anotação para injeção de dependência
import org.springframework.http.HttpStatus; // Importa a classe HttpStatus
import org.springframework.http.ResponseEntity; // Importa a classe ResponseEntity
import org.springframework.web.bind.annotation.*; // Importa as anotações para o controlador

import java.util.List; // Importa a classe List

@RestController // Indica que esta classe é um controlador REST
@RequestMapping("/api/ingredientes") // Mapeia requisições para /api/ingredientes
public class IngredienteController {

    @Autowired // Injeção de dependência do repositório de ingredientes
    private IngredienteRepository ingredienteRepository;

    @GetMapping // Mapeia requisições GET para obter todos os ingredientes
    public List<Ingrediente> getAllIngredientes() {
        return ingredienteRepository.findAll(); // Retorna todos os ingredientes do banco de dados
    }

    @PostMapping // Mapeia requisições POST para criar um novo ingrediente
    public ResponseEntity<Ingrediente> createIngrediente(@RequestBody Ingrediente ingrediente) {
        Ingrediente savedIngrediente = ingredienteRepository.save(ingrediente); // Salva o novo ingrediente no banco de dados
        return new ResponseEntity<>(savedIngrediente, HttpStatus.CREATED); // Retorna o ingrediente salvo com status 201
    }

    @PutMapping("/{id}") // Mapeia requisições PUT para atualizar um ingrediente existente
    public ResponseEntity<Ingrediente> updateIngrediente(@PathVariable Long id, @RequestBody Ingrediente ingrediente) {
        Ingrediente existingIngrediente = ingredienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ingrediente not found")); // Lança exceção se o ingrediente não for encontrado
        existingIngrediente.setNome(ingrediente.getNome()); // Atualiza o nome do ingrediente
        existingIngrediente.setQuantidade(ingrediente.getQuantidade()); // Atualiza a quantidade do ingrediente
        existingIngrediente.setUnidadeMedida(ingrediente.getUnidadeMedida()); // Atualiza a unidade de medida do ingrediente
        Ingrediente updatedIngrediente = ingredienteRepository.save(existingIngrediente); // Salva o ingrediente atualizado no banco de dados
        return ResponseEntity.ok(updatedIngrediente); // Retorna o ingrediente atualizado
    }

    @DeleteMapping("/{id}") // Mapeia requisições DELETE para excluir um ingrediente
    public ResponseEntity<Void> deleteIngrediente(@PathVariable Long id) {
        if (!ingredienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Ingrediente not found"); // Lança exceção se o ingrediente não existir
        }
        ingredienteRepository.deleteById(id); // Exclui o ingrediente do banco de dados
        return ResponseEntity.noContent().build(); // Retorna resposta 204 (sem conteúdo)
    }
}
