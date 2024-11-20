//Feito Por: 
// Leonardo De Castro Tonon Ra: 10426930
//MATHEUS CALEIRO PINHEIRO RA: 10418688
//JOAO PEDRO FERNANDES MILHOMENS RA: 10417578

package br.leonardo.receitas.portal_de.receitas.controllers;

import br.leonardo.receitas.portal_de.receitas.entidades.Avaliacao;
import br.leonardo.receitas.portal_de.receitas.entidades.Usuario;
import br.leonardo.receitas.portal_de.receitas.entidades.Receita;
import br.leonardo.receitas.portal_de.receitas.exceptions.ResourceNotFoundException;
import br.leonardo.receitas.portal_de.receitas.repositories.AvaliacaoRepository;
import br.leonardo.receitas.portal_de.receitas.repositories.ReceitaRepository;
import br.leonardo.receitas.portal_de.receitas.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

import java.util.List;

@RestController
@RequestMapping("/api/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ReceitaRepository receitaRepository;

    // Verificar usuário logado
    private Usuario getLoggedUser(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            throw new IllegalStateException("Usuário não está logado");
        }
        return usuarioRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + userId));
    }

    // Obter todas as avaliações
    @GetMapping
    public List<Avaliacao> getAllAvaliacoes() {
        return avaliacaoRepository.findAll();
    }

    // Criar nova avaliação
    @PostMapping("/{receitaId}")
    public ResponseEntity<Avaliacao> createAvaliacao(@PathVariable Long receitaId, @RequestBody Avaliacao avaliacao, HttpSession session) {
        Usuario usuarioLogado = getLoggedUser(session);
        Receita receita = receitaRepository.findById(receitaId)
                .orElseThrow(() -> new ResourceNotFoundException("Receita não encontrada com ID: " + receitaId));

        avaliacao.setReceita(receita);
        avaliacao.setUsuario(usuarioLogado);

        Avaliacao savedAvaliacao = avaliacaoRepository.save(avaliacao);
        return new ResponseEntity<>(savedAvaliacao, HttpStatus.CREATED);
    }

    // Atualizar avaliação
    @PutMapping("/{id}")
    public ResponseEntity<Avaliacao> updateAvaliacao(@PathVariable Long id, @RequestBody Avaliacao avaliacao, HttpSession session) {
        Usuario usuarioLogado = getLoggedUser(session);
        Avaliacao existingAvaliacao = avaliacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Avaliacao não encontrada com ID: " + id));

        if (!existingAvaliacao.getUsuario().getId().equals(usuarioLogado.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        existingAvaliacao.setEstrelas(avaliacao.getEstrelas());
        Avaliacao updatedAvaliacao = avaliacaoRepository.save(existingAvaliacao);
        return ResponseEntity.ok(updatedAvaliacao);
    }

    // Deletar avaliação
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvaliacao(@PathVariable Long id, HttpSession session) {
        Usuario usuarioLogado = getLoggedUser(session);
        Avaliacao avaliacao = avaliacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Avaliacao não encontrada com ID: " + id));

        if (!avaliacao.getUsuario().getId().equals(usuarioLogado.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        avaliacaoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
