package br.leonardo.receitas.portal_de.receitas.controllers;

import br.leonardo.receitas.portal_de.receitas.entidades.Receita;
import  br.leonardo.receitas.portal_de.receitas.repositories.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/receitas")
public class ReceitaController {

    @Autowired
    private ReceitaRepository receitaRepository;

    @GetMapping
    public List<Receita> getAllReceitas() {
        return receitaRepository.findAll(); // Acesso aberto a todos
    }

    @PreAuthorize("hasRole('USER')") // Apenas usuários autenticados podem adicionar receitas
    @PostMapping
    public Receita createReceita(@RequestBody Receita receita) {
        return receitaRepository.save(receita);
    }

    @PreAuthorize("hasRole('USER')") // Apenas usuários autenticados podem editar receitas
    @PutMapping("/{id}")
    public Receita updateReceita(@PathVariable Long id, @RequestBody Receita receita) {
        Receita existingReceita = receitaRepository.findById(id).orElseThrow();
        existingReceita.setNome(receita.getNome());
        existingReceita.setDescricao(receita.getDescricao());
        return receitaRepository.save(existingReceita);
    }

    @PreAuthorize("hasRole('USER')") // Apenas usuários autenticados podem deletar receitas
    @DeleteMapping("/{id}")
    public void deleteReceita(@PathVariable Long id) {
        receitaRepository.deleteById(id);
    }
}
