package br.leonardo.receitas.portal.controllers;

import br.leonardo.receitas.portal.Categoria;
import br.leonardo.receitas.portal.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    @PostMapping
    public Categoria createCategoria(@RequestBody Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @PutMapping("/{id}")
    public Categoria updateCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
        Categoria existingCategoria = categoriaRepository.findById(id).orElseThrow();
        existingCategoria.setNome(categoria.getNome());
        return categoriaRepository.save(existingCategoria);
    }

    @DeleteMapping("/{id}")
    public void deleteCategoria(@PathVariable Long id) {
        categoriaRepository.deleteById(id);
    }
}
