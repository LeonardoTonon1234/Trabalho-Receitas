package br.leonardo.receitas.portal_de.receitas.controllers;

import br.leonardo.receitas.portal_de.receitas.entidades.Avaliacao;
import br.leonardo.receitas.portal_de.receitas.repositories.AvaliacaoRepository;
import br.leonardo.receitas.portal_de.receitas.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @GetMapping
    public List<Avaliacao> getAllAvaliacoes() {
        return avaliacaoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Avaliacao> createAvaliacao(@RequestBody Avaliacao avaliacao) {
        Avaliacao savedAvaliacao = avaliacaoRepository.save(avaliacao);
        return new ResponseEntity<>(savedAvaliacao, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Avaliacao> updateAvaliacao(@PathVariable Long id, @RequestBody Avaliacao avaliacao) {
        Avaliacao existingAvaliacao = avaliacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Avaliacao not found with id: " + id));
        existingAvaliacao.setEstrelas(avaliacao.getEstrelas());
        existingAvaliacao.setReceita(avaliacao.getReceita());
        existingAvaliacao.setUsuario(avaliacao.getUsuario());
        Avaliacao updatedAvaliacao = avaliacaoRepository.save(existingAvaliacao);
        return ResponseEntity.ok(updatedAvaliacao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvaliacao(@PathVariable Long id) {
        if (!avaliacaoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Avaliacao not found with id: " + id);
        }
        avaliacaoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
