//Feito Por: 
// Leonardo De Castro Tonon Ra: 10426930
//MATHEUS CALEIRO PINHEIRO RA: 10418688
//JOAO PEDRO FERNANDES MILHOMENS RA: 10417578

package br.leonardo.receitas.portal_de.receitas.controllers;

import br.leonardo.receitas.portal_de.receitas.entidades.Usuario; // Importa a entidade Usuario
import br.leonardo.receitas.portal_de.receitas.repositories.UsuarioRepository; // Importa o repositório de Usuário
import org.springframework.beans.factory.annotation.Autowired; // Importa a anotação para injeção de dependência
import org.springframework.http.HttpStatus; // Importa a classe HttpStatus
import org.springframework.http.ResponseEntity; // Importa a classe ResponseEntity
import org.springframework.web.bind.annotation.*; // Importa as anotações para o controlador

import jakarta.servlet.http.HttpSession; // Importa a sessão para controle de login
import java.util.List; // Importa a classe List
import java.util.Optional; // Importa a classe Optional

@RestController // Indica que esta classe é um controlador REST
@RequestMapping("/api/usuarios") // Mapeia requisições para /api/usuarios
public class UsuarioController {

    @Autowired // Injeção de dependência do repositório de usuários
    private UsuarioRepository usuarioRepository;

    // Verifica se o usuário está autenticado
    @GetMapping("/autenticado")
    public ResponseEntity<Boolean> isAuthenticated(HttpSession session) {
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        return ResponseEntity.ok(isLoggedIn != null && isLoggedIn);
    }

    @GetMapping // Mapeia requisições GET para obter todos os usuários
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll(); // Retorna todos os usuários do banco de dados
    }

    @PostMapping("/register") // Mapeia requisições POST para registrar um novo usuário
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        Optional<Usuario> existingUsuario = usuarioRepository.findByEmail(usuario.getEmail());
        if (existingUsuario.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return new ResponseEntity<>(savedUsuario, HttpStatus.CREATED);
    }

    @PostMapping("/login") // Mapeia requisições POST para fazer login
    public ResponseEntity<String> login(@RequestBody Usuario usuario, HttpSession session) {
        Optional<Usuario> existingUsuario = usuarioRepository.findByEmail(usuario.getEmail());
        if (existingUsuario.isPresent() && existingUsuario.get().getSenha().equals(usuario.getSenha())) {
            session.setAttribute("isLoggedIn", true);
            return ResponseEntity.ok("Login bem-sucedido!");
        }
        session.setAttribute("isLoggedIn", false);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
    }

    @PostMapping("/logout") // Mapeia requisições POST para logout
    public ResponseEntity<Void> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}") // Mapeia requisições PUT para atualizar um usuário existente
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario existingUsuario = usuarioRepository.findById(id).orElseThrow();
        existingUsuario.setNome(usuario.getNome());
        existingUsuario.setEmail(usuario.getEmail());
        existingUsuario.setSenha(usuario.getSenha());
        existingUsuario.setAdmin(usuario.isAdmin());
        return new ResponseEntity<>(usuarioRepository.save(existingUsuario), HttpStatus.OK);
    }

    @DeleteMapping("/{id}") // Mapeia requisições DELETE para excluir um usuário
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
