package br.leonardo.receitas.portal_de.receitas.controllers;

import br.leonardo.receitas.portal_de.receitas.entidades.Comentario;
import br.leonardo.receitas.portal_de.receitas.repositories.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Comentario createComentario(@RequestBody Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    @PutMapping("/{id}")
    public Comentario updateComentario(@PathVariable Long id, @RequestBody Comentario comentario) {
        Comentario existingComentario = comentarioRepository.findById(id).orElseThrow();
        existingComentario.setTexto(comentario.getTexto());
        existingComentario.setReceita(comentario.getReceita());
        existingComentario.setUsuario(comentario.getUsuario());
        return comentarioRepository.save(existingComentario);
    }

    @DeleteMapping("/{id}")
    public void deleteComentario(@PathVariable Long id) {
        comentarioRepository.deleteById(id);
    }
}
