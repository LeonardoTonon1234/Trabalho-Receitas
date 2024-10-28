package br.leonardo.receitas.portal_de.receitas.controllers;

import br.leonardo.receitas.portal_de.receitas.entidades.Passo;
import br.leonardo.receitas.portal_de.receitas.repositories.PassoRepository;
import br.leonardo.receitas.portal_de.receitas.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passos")
public class PassoController {

    @Autowired
    private PassoRepository passoRepository;

    @GetMapping
    public List<Passo> getAllPassos() {
        return passoRepository.findAll(); // Todos podem ver os passos
    }

    @PostMapping
    public ResponseEntity<Passo> createPasso(@RequestBody Passo passo) {
        Passo savedPasso = passoRepository.save(passo);
        return new ResponseEntity<>(savedPasso, HttpStatus.CREATED); // Retorna o passo criado
    }

    @PutMapping("/{id}")
    public ResponseEntity<Passo> updatePasso(@PathVariable Long id, @RequestBody Passo passo) {
        Passo existingPasso = passoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Passo not found with id: " + id));
        existingPasso.setDescricao(passo.getDescricao());
        existingPasso.setOrdem(passo.getOrdem());
        existingPasso.setReceita(passo.getReceita());
        Passo updatedPasso = passoRepository.save(existingPasso);
        return ResponseEntity.ok(updatedPasso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePasso(@PathVariable Long id) {
        if (!passoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Passo not found with id: " + id);
        }
        passoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
