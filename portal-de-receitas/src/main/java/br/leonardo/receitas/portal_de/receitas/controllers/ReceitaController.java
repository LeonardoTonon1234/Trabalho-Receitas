package br.leonardo.receitas.portal_de.receitas.controllers;

import br.leonardo.receitas.portal_de.receitas.entidades.Receita;
import br.leonardo.receitas.portal_de.receitas.entidades.Usuario;
import br.leonardo.receitas.portal_de.receitas.exceptions.ResourceNotFoundException;
import br.leonardo.receitas.portal_de.receitas.repositories.ReceitaRepository;
import br.leonardo.receitas.portal_de.receitas.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession; // Importando a sessão para controle de login

import java.util.List;

@RestController
@RequestMapping("/api/receitas")
public class ReceitaController {

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Método para verificar se o usuário está logado
    private Usuario getLoggedUser(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            throw new IllegalStateException("Usuário não está logado");
        }
        return usuarioRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + userId));
    }

    // Obter todas as receitas
    @GetMapping
    public List<Receita> getAllReceitas() {
        return receitaRepository.findAll();
    }

    // Obter receita por ID
    @GetMapping("/{id}")
    public ResponseEntity<Receita> getReceitaById(@PathVariable Long id) {
        Receita receita = receitaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receita não encontrada com ID: " + id));
        return ResponseEntity.ok(receita);
    }

    // Criar uma nova receita
    @PostMapping
    public ResponseEntity<Receita> createReceita(@RequestBody Receita receita, HttpSession session) {
        Usuario usuarioLogado = getLoggedUser(session); // Obtém o usuário logado
        receita.setUsuario(usuarioLogado); // Associa a receita ao usuário logado
        Receita savedReceita = receitaRepository.save(receita);
        return new ResponseEntity<>(savedReceita, HttpStatus.CREATED);
    }

    // Atualizar uma receita existente
    @PutMapping("/{id}")
    public ResponseEntity<Receita> updateReceita(@PathVariable Long id, @RequestBody Receita receitaAtualizada, HttpSession session) {
        Usuario usuarioLogado = getLoggedUser(session); // Obtém o usuário logado
        Receita receita = receitaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receita não encontrada com ID: " + id));
        
        // Verifica se o usuário logado é o autor da receita
        if (!receita.getUsuario().getId().equals(usuarioLogado.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Retorna erro de acesso proibido
        }

        receita.setNome(receitaAtualizada.getNome());
        receita.setDescricao(receitaAtualizada.getDescricao());
        receita.setCategoria(receitaAtualizada.getCategoria());
        Receita updatedReceita = receitaRepository.save(receita);
        return ResponseEntity.ok(updatedReceita);
    }

    // Deletar uma receita
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReceita(@PathVariable Long id, HttpSession session) {
        Usuario usuarioLogado = getLoggedUser(session); // Obtém o usuário logado
        Receita receita = receitaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receita não encontrada com ID: " + id));
        
        // Verifica se o usuário logado é o autor da receita
        if (!receita.getUsuario().getId().equals(usuarioLogado.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Retorna erro de acesso proibido
        }

        receitaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Buscar receitas por nome
    @GetMapping("/buscar")
    public List<Receita> buscarReceitasPorNome(@RequestParam String nome) {
        return receitaRepository.findByNomeContainingIgnoreCase(nome);
    }

    // Obter receitas por categoria
    @GetMapping("/por-categoria/{categoriaId}")
    public List<Receita> getReceitasByCategoria(@PathVariable Long categoriaId) {
        return receitaRepository.findByCategoria_Id(categoriaId);
    }

    // Obter detalhes da receita por ID
    @GetMapping("/{id}/detalhes")
    public ResponseEntity<Receita> getReceitaDetalhes(@PathVariable Long id) {
        Receita receita = receitaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Receita não encontrada com o ID: " + id));
        return ResponseEntity.ok(receita);
    }
}
