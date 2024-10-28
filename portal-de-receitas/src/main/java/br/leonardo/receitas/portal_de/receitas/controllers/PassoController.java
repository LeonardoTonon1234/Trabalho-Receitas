package br.leonardo.receitas.portal_de.receitas.controllers;

import br.leonardo.receitas.portal_de.receitas.entidades.Passo;
import br.leonardo.receitas.portal_de.receitas.repositories.PassoRepository;
import br.leonardo.receitas.portal_de.receitas.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passos")
public class PassoController {

    @Autowired
    private PassoRepository passoRepository;

    @GetMapping
    public List<Passo> getAllPassos() {
        return passoRepository.findAll();
    }

    @PreAuthorize("isAuthenticated()") // Apenas usuários autenticados podem criar passos
    @PostMapping
    public ResponseEntity<Passo> createPasso(@RequestBody Passo passo) {
        Passo savedPasso = passoRepository.save(passo);
        return new ResponseEntity<>(savedPasso, HttpStatus.CREATED);
    }

    @PreAuthorize("isAuthenticated()") // Apenas usuários autenticados podem atualizar passos
    @PutMapping("/{id}")
    public ResponseEntity<Passo> updatePasso(@PathVariable Long id, @RequestBody Passo passo) {
        Passo existingPasso = passoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Passo not found"));
        existingPasso.setDescricao(passo.getDescricao());
        existingPasso.setOrdem(passo.getOrdem());
        existingPasso.setReceita(passo.getReceita());
        Passo updatedPasso = passoRepository.save(existingPasso);
        return ResponseEntity.ok(updatedPasso);
    }

    @PreAuthorize("isAuthenticated()") // Apenas usuários autenticados podem deletar passos
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePasso(@PathVariable Long id) {
        if (!passoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Passo not found");
        }
        passoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
