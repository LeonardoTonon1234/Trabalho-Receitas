package br.leonardo.receitas.portal_de.receitas.controllers;

import br.leonardo.receitas.portal_de.receitas.entidades.Passo; // Importa a entidade Passo
import br.leonardo.receitas.portal_de.receitas.repositories.PassoRepository; // Importa o repositório de Passo
import br.leonardo.receitas.portal_de.receitas.exceptions.ResourceNotFoundException; // Importa a exceção personalizada
import org.springframework.beans.factory.annotation.Autowired; // Importa a anotação para injeção de dependência
import org.springframework.http.HttpStatus; // Importa a classe HttpStatus
import org.springframework.http.ResponseEntity; // Importa a classe ResponseEntity
import org.springframework.web.bind.annotation.*; // Importa as anotações para o controlador

import java.util.List; // Importa a classe List

@RestController // Indica que esta classe é um controlador REST
@RequestMapping("/api/passos") // Mapeia requisições para /api/passos
public class PassoController {

    @Autowired // Injeção de dependência do repositório de passos
    private PassoRepository passoRepository;

    @GetMapping // Mapeia requisições GET para obter todos os passos
    public List<Passo> getAllPassos() {
        return passoRepository.findAll(); // Retorna todos os passos do banco de dados
    }

    @PostMapping // Mapeia requisições POST para criar um novo passo
    public ResponseEntity<Passo> createPasso(@RequestBody Passo passo) {
        Passo savedPasso = passoRepository.save(passo); // Salva o novo passo no banco de dados
        return new ResponseEntity<>(savedPasso, HttpStatus.CREATED); // Retorna o passo salvo com status 201
    }

    @PutMapping("/{id}") // Mapeia requisições PUT para atualizar um passo existente
    public ResponseEntity<Passo> updatePasso(@PathVariable Long id, @RequestBody Passo passo) {
        Passo existingPasso = passoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Passo not found with id: " + id)); // Lança exceção se o passo não for encontrado
        existingPasso.setDescricao(passo.getDescricao()); // Atualiza a descrição do passo
        existingPasso.setOrdem(passo.getOrdem()); // Atualiza a ordem do passo
        existingPasso.setReceita(passo.getReceita()); // Atualiza a receita associada ao passo
        Passo updatedPasso = passoRepository.save(existingPasso); // Salva o passo atualizado no banco de dados
        return ResponseEntity.ok(updatedPasso); // Retorna o passo atualizado
    }

    @DeleteMapping("/{id}") // Mapeia requisições DELETE para excluir um passo
    public ResponseEntity<Void> deletePasso(@PathVariable Long id) {
        if (!passoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Passo not found with id: " + id); // Lança exceção se o passo não existir
        }
        passoRepository.deleteById(id); // Exclui o passo do banco de dados
        return ResponseEntity.noContent().build(); // Retorna resposta 204 (sem conteúdo)
    }
}
