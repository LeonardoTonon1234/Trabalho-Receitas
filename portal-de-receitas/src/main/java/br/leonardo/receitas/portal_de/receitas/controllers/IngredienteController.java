package br.leonardo.receitas.portal_de.receitas.controllers;

import br.leonardo.receitas.portal_de.receitas.entidades.Ingrediente;
import br.leonardo.receitas.portal_de.receitas.repositories.IngredienteRepository;
import br.leonardo.receitas.portal_de.receitas.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("isAuthenticated()") // Apenas usuários autenticados podem criar ingredientes
    @PostMapping
    public Ingrediente createIngrediente(@RequestBody Ingrediente ingrediente) {
        return ingredienteRepository.save(ingrediente);
    }

    @PreAuthorize("isAuthenticated()") // Apenas usuários autenticados podem atualizar ingredientes
    @PutMapping("/{id}")
    public Ingrediente updateIngrediente(@PathVariable Long id, @RequestBody Ingrediente ingrediente) {
        Ingrediente existingIngrediente = ingredienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ingrediente not found"));
        existingIngrediente.setNome(ingrediente.getNome());
        existingIngrediente.setQuantidade(ingrediente.getQuantidade());
        existingIngrediente.setUnidadeMedida(ingrediente.getUnidadeMedida());
        return ingredienteRepository.save(existingIngrediente);
    }

    @PreAuthorize("isAuthenticated()") // Apenas usuários autenticados podem deletar ingredientes
    @DeleteMapping("/{id}")
    public void deleteIngrediente(@PathVariable Long id) {
        ingredienteRepository.deleteById(id);
    }
}
