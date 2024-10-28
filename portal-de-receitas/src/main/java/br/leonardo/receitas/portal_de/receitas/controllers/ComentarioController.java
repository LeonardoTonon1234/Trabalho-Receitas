package br.leonardo.receitas.portal_de.receitas.controllers;

import br.leonardo.receitas.portal_de.receitas.entidades.Comentario;
import br.leonardo.receitas.portal_de.receitas.repositories.ComentarioRepository;
import br.leonardo.receitas.portal_de.receitas.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indica que esta classe é um controlador REST
@RequestMapping("/api/comentarios") // Mapeia requisições para /api/comentarios
public class ComentarioController {

    @Autowired // Injeção de dependência do repositório de comentários
    private ComentarioRepository comentarioRepository;

    @GetMapping // Mapeia requisições GET para obter todos os comentários
    public List<Comentario> getAllComentarios() {
        return comentarioRepository.findAll(); // Retorna todos os comentários do banco de dados
    }

    @PostMapping // Mapeia requisições POST para criar um novo comentário
    public ResponseEntity<Comentario> createComentario(@RequestBody Comentario comentario) {
        Comentario savedComentario = comentarioRepository.save(comentario); // Salva o novo comentário no banco de dados
        return new ResponseEntity<>(savedComentario, HttpStatus.CREATED); // Retorna o comentário salvo com status 201
    }

    @PutMapping("/{id}") // Mapeia requisições PUT para atualizar um comentário existente
    public ResponseEntity<Comentario> updateComentario(@PathVariable Long id, @RequestBody Comentario comentario) {
        Comentario existingComentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comentario not found with id: " + id)); // Lança exceção se o comentário não for encontrado
        existingComentario.setTexto(comentario.getTexto()); // Atualiza o texto do comentário
        existingComentario.setReceita(comentario.getReceita()); // Atualiza a receita associada ao comentário
        existingComentario.setUsuario(comentario.getUsuario()); // Atualiza o usuário associado ao comentário
        Comentario updatedComentario = comentarioRepository.save(existingComentario); // Salva o comentário atualizado no banco de dados
        return ResponseEntity.ok(updatedComentario); // Retorna o comentário atualizado
    }

    @DeleteMapping("/{id}") // Mapeia requisições DELETE para excluir um comentário
    public ResponseEntity<Void> deleteComentario(@PathVariable Long id) {
        if (!comentarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Comentario not found with id: " + id); // Lança exceção se o comentário não existir
        }
        comentarioRepository.deleteById(id); // Exclui o comentário do banco de dados
        return ResponseEntity.noContent().build(); // Retorna resposta 204 (sem conteúdo)
    }
}
