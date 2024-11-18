//Feito Por: 
// Leonardo De Castro Tonon Ra: 10426930
// MATHEUS CALEIRO PINHEIRO RA: 10418688
// JOAO PEDRO FERNANDES MILHOMENS RA: 10417578

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
        return passoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Passo> getPassoById(@PathVariable Long id) {
        Passo passo = passoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Passo não encontrado com ID: " + id));
        return ResponseEntity.ok(passo);
    }

    @PostMapping
    public ResponseEntity<Passo> createPasso(@RequestBody Passo passo) {
        Passo savedPasso = passoRepository.save(passo);
        return new ResponseEntity<>(savedPasso, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Passo> updatePasso(@PathVariable Long id, @RequestBody Passo passoAtualizado) {
        Passo passo = passoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Passo não encontrado com ID: " + id));
        passo.setDescricao(passoAtualizado.getDescricao());
        passo.setOrdem(passoAtualizado.getOrdem());
        Passo updatedPasso = passoRepository.save(passo);
        return ResponseEntity.ok(updatedPasso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePasso(@PathVariable Long id) {
        if (!passoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Passo não encontrado com ID: " + id);
        }
        passoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/receita/{receitaId}")
    public List<Passo> getPassosByReceita(@PathVariable Long receitaId) {
        return passoRepository.findByReceitaIdOrderByOrdem(receitaId);
    }
}
