//Feito Por: 
// Leonardo De Castro Tonon Ra: 10426930
// MATHEUS CALEIRO PINHEIRO RA: 10418688
// JOAO PEDRO FERNANDES MILHOMENS RA: 10417578

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
        return ingredienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingrediente> getIngredienteById(@PathVariable Long id) {
        Ingrediente ingrediente = ingredienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ingrediente não encontrado com ID: " + id));
        return ResponseEntity.ok(ingrediente);
    }

    @PostMapping
    public ResponseEntity<Ingrediente> createIngrediente(@RequestBody Ingrediente ingrediente) {
        Ingrediente savedIngrediente = ingredienteRepository.save(ingrediente);
        return new ResponseEntity<>(savedIngrediente, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingrediente> updateIngrediente(@PathVariable Long id, @RequestBody Ingrediente ingredienteAtualizado) {
        Ingrediente ingrediente = ingredienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ingrediente não encontrado com ID: " + id));
        ingrediente.setNome(ingredienteAtualizado.getNome());
        ingrediente.setQuantidade(ingredienteAtualizado.getQuantidade());
        ingrediente.setUnidadeMedida(ingredienteAtualizado.getUnidadeMedida());
        Ingrediente updatedIngrediente = ingredienteRepository.save(ingrediente);
        return ResponseEntity.ok(updatedIngrediente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngrediente(@PathVariable Long id) {
        if (!ingredienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Ingrediente não encontrado com ID: " + id);
        }
        ingredienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
