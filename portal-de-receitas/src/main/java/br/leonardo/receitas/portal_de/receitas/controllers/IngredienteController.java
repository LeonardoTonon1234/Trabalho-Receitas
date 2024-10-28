package br.leonardo.receitas.portal_de.receitas.controllers;

import br.leonardo.receitas.portal_de.receitas.entidades.Ingrediente;
import br.leonardo.receitas.portal_de.receitas.repositories.IngredienteRepository;
import br.leonardo.receitas.portal_de.receitas.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredientes")
public class IngredienteController {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @GetMapping
    public List<Ingrediente> getAllIngredientes() {
        return ingredienteRepository.findAll(); // Todos podem ver os ingredientes
    }

    @PostMapping
    public ResponseEntity<Ingrediente> createIngrediente(@RequestBody Ingrediente ingrediente) {
        Ingrediente savedIngrediente = ingredienteRepository.save(ingrediente);
        return new ResponseEntity<>(savedIngrediente, HttpStatus.CREATED); // Retorna o ingrediente criado
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingrediente> updateIngrediente(@PathVariable Long id, @RequestBody Ingrediente ingrediente) {
        Ingrediente existingIngrediente = ingredienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ingrediente not found"));
        existingIngrediente.setNome(ingrediente.getNome());
        existingIngrediente.setQuantidade(ingrediente.getQuantidade());
        existingIngrediente.setUnidadeMedida(ingrediente.getUnidadeMedida());
        Ingrediente updatedIngrediente = ingredienteRepository.save(existingIngrediente);
        return ResponseEntity.ok(updatedIngrediente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngrediente(@PathVariable Long id) {
        if (!ingredienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Ingrediente not found");
        }
        ingredienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
