//Feito Por: 
// Leonardo De Castro Tonon Ra: 10426930
//MATHEUS CALEIRO PINHEIRO RA: 10418688
//JOAO PEDRO FERNANDES MILHOMENS RA: 10417578

package br.leonardo.receitas.portal_de.receitas.controllers;

import br.leonardo.receitas.portal_de.receitas.entidades.Comentario;
import br.leonardo.receitas.portal_de.receitas.entidades.Receita;
import br.leonardo.receitas.portal_de.receitas.entidades.Usuario;
import br.leonardo.receitas.portal_de.receitas.exceptions.ResourceNotFoundException;
import br.leonardo.receitas.portal_de.receitas.repositories.ComentarioRepository;
import br.leonardo.receitas.portal_de.receitas.repositories.ReceitaRepository;
import br.leonardo.receitas.portal_de.receitas.repositories.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Retorna todos os comentários associados a uma receita
    @GetMapping("/{receitaId}")
    public List<Comentario> getComentariosByReceita(@PathVariable Long receitaId) {
        return comentarioRepository.findByReceita_Id(receitaId);
    }

    // Criação de um comentário
    @PostMapping("/{receitaId}")
    public ResponseEntity<Comentario> createComentario(
            @PathVariable Long receitaId,
            @RequestBody Comentario comentario,
            HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Receita receita = receitaRepository.findById(receitaId)
                .orElseThrow(() -> new ResourceNotFoundException("Receita não encontrada com ID: " + receitaId));
        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + userId));

        comentario.setReceita(receita);
        comentario.setUsuario(usuario);
        Comentario savedComentario = comentarioRepository.save(comentario);

        return new ResponseEntity<>(savedComentario, HttpStatus.CREATED);
    }
}
