package br.leonardo.receitas.portal.controllers;

import br.leonardo.receitas.portal.Usuario;
import br.leonardo.receitas.portal.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @PutMapping("/{id}")
    public Usuario updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario existingUsuario = usuarioRepository.findById(id).orElseThrow();
        existingUsuario.setNome(usuario.getNome());
        existingUsuario.setEmail(usuario.getEmail());
        existingUsuario.setSenha(usuario.getSenha());
        existingUsuario.setAdmin(usuario.isAdmin());
        return usuarioRepository.save(existingUsuario);
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }
}
