package br.leonardo.receitas.portal_de.receitas.controllers;

import br.leonardo.receitas.portal_de.receitas.entidades.Passo;
import br.leonardo.receitas.portal_de.receitas.repositories.PassoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping
    public Passo createPasso(@RequestBody Passo passo) {
        return passoRepository.save(passo);
    }

    @PutMapping("/{id}")
    public Passo updatePasso(@PathVariable Long id, @RequestBody Passo passo) {
        Passo existingPasso = passoRepository.findById(id).orElseThrow();
        existingPasso.setDescricao(passo.getDescricao());
        existingPasso.setOrdem(passo.getOrdem());
        existingPasso.setReceita(passo.getReceita());
        return passoRepository.save(existingPasso);
    }

    @DeleteMapping("/{id}")
    public void deletePasso(@PathVariable Long id) {
        passoRepository.deleteById(id);
    }
}
