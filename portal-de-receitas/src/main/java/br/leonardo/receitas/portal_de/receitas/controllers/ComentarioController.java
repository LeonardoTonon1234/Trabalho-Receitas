package br.leonardo.receitas.portal_de.receitas.controllers;

import br.leonardo.receitas.portal_de.receitas.entidades.Comentario;
import br.leonardo.receitas.portal_de.receitas.repositories.ComentarioRepository;
import br.leonardo.receitas.portal_de.receitas.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @GetMapping
    public List<Comentario> getAllComentarios() {
        return comentarioRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Comentario> createComentario(@RequestBody Comentario comentario) {
        Comentario savedComentario = comentarioRepository.save(comentario);
        return new ResponseEntity<>(savedComentario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comentario> updateComentario(@PathVariable Long id, @RequestBody Comentario comentario) {
        Comentario existingComentario = comentarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comentario not found with id: " + id));
        existingComentario.setTexto(comentario.getTexto());
        existingComentario.setReceita(comentario.getReceita());
        existingComentario.setUsuario(comentario.getUsuario());
        Comentario updatedComentario = comentarioRepository.save(existingComentario);
        return ResponseEntity.ok(updatedComentario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComentario(@PathVariable Long id) {
        if (!comentarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Comentario not found with id: " + id);
        }
        comentarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
