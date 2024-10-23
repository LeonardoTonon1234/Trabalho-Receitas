package br.leonardo.receitas.portal.controllers;

import br.leonardo.receitas.portal.Avaliacao;
import br.leonardo.receitas.portal.repositories.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Avaliacao createAvaliacao(@RequestBody Avaliacao avaliacao) {
        return avaliacaoRepository.save(avaliacao);
    }

    @PutMapping("/{id}")
    public Avaliacao updateAvaliacao(@PathVariable Long id, @RequestBody Avaliacao avaliacao) {
        Avaliacao existingAvaliacao = avaliacaoRepository.findById(id).orElseThrow();
        existingAvaliacao.setEstrelas(avaliacao.getEstrelas());
        existingAvaliacao.setReceita(avaliacao.getReceita());
        existingAvaliacao.setUsuario(avaliacao.getUsuario());
        return avaliacaoRepository.save(existingAvaliacao);
    }

    @DeleteMapping("/{id}")
    public void deleteAvaliacao(@PathVariable Long id) {
        avaliacaoRepository.deleteById(id);
    }
}
