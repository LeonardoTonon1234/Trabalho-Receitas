package br.leonardo.receitas.portal_de.receitas.controllers;

import br.leonardo.receitas.portal_de.receitas.entidades.Ingrediente;
import br.leonardo.receitas.portal_de.receitas.repositories.IngredienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public Ingrediente createIngrediente(@RequestBody Ingrediente ingrediente) {
        return ingredienteRepository.save(ingrediente);
    }

    @PutMapping("/{id}")
    public Ingrediente updateIngrediente(@PathVariable Long id, @RequestBody Ingrediente ingrediente) {
        Ingrediente existingIngrediente = ingredienteRepository.findById(id).orElseThrow();
        existingIngrediente.setNome(ingrediente.getNome());
        existingIngrediente.setQuantidade(ingrediente.getQuantidade());
        existingIngrediente.setUnidadeMedida(ingrediente.getUnidadeMedida());
        return ingredienteRepository.save(existingIngrediente);
    }

    @DeleteMapping("/{id}")
    public void deleteIngrediente(@PathVariable Long id) {
        ingredienteRepository.deleteById(id);
    }
}
