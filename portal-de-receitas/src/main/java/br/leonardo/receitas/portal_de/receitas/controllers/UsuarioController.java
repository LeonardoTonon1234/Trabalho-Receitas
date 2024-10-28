package br.leonardo.receitas.portal_de.receitas.controllers;

import br.leonardo.receitas.portal_de.receitas.entidades.Usuario; // Importa a entidade Usuario
import br.leonardo.receitas.portal_de.receitas.repositories.UsuarioRepository; // Importa o repositório de Usuário
import org.springframework.beans.factory.annotation.Autowired; // Importa a anotação para injeção de dependência
import org.springframework.http.HttpStatus; // Importa a classe HttpStatus
import org.springframework.http.ResponseEntity; // Importa a classe ResponseEntity
import org.springframework.web.bind.annotation.*; // Importa as anotações para o controlador

import java.util.List; // Importa a classe List
import java.util.Optional; // Importa a classe Optional

@RestController // Indica que esta classe é um controlador REST
@RequestMapping("/api/usuarios") // Mapeia requisições para /api/usuarios
public class UsuarioController {

    @Autowired // Injeção de dependência do repositório de usuários
    private UsuarioRepository usuarioRepository;

    @GetMapping // Mapeia requisições GET para obter todos os usuários
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll(); // Retorna todos os usuários do banco de dados
    }

    @PostMapping("/register") // Mapeia requisições POST para registrar um novo usuário
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        // Verificação opcional para garantir que o e-mail é único, se necessário
        Optional<Usuario> existingUsuario = usuarioRepository.findByEmail(usuario.getEmail());
        if (existingUsuario.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null); // Retorna conflito se o usuário já existir
        }
        
        // Salva o novo usuário no banco de dados
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return new ResponseEntity<>(savedUsuario, HttpStatus.CREATED); // Retorna o usuário criado com status 201
    }

    @PostMapping("/login") // Mapeia requisições POST para fazer login
    public ResponseEntity<String> login(@RequestBody Usuario usuario) {
        // Busca o usuário pelo email fornecido
        Optional<Usuario> existingUsuario = usuarioRepository.findByEmail(usuario.getEmail());
        
        // Verifica se o usuário existe e se a senha está correta
        if (existingUsuario.isPresent() && existingUsuario.get().getSenha().equals(usuario.getSenha())) {
            return ResponseEntity.ok("Login bem-sucedido!"); // Retorna mensagem de sucesso
        }
        
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas"); // Retorna erro de credenciais inválidas
    }

    @PutMapping("/{id}") // Mapeia requisições PUT para atualizar um usuário existente
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        // Busca o usuário pelo ID, lança exceção se não encontrado
        Usuario existingUsuario = usuarioRepository.findById(id).orElseThrow();
        // Atualiza os dados do usuário
        existingUsuario.setNome(usuario.getNome());
        existingUsuario.setEmail(usuario.getEmail());
        existingUsuario.setSenha(usuario.getSenha());
        existingUsuario.setAdmin(usuario.isAdmin());
        return new ResponseEntity<>(usuarioRepository.save(existingUsuario), HttpStatus.OK); // Retorna o usuário atualizado
    }

    @DeleteMapping("/{id}") // Mapeia requisições DELETE para excluir um usuário
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id); // Exclui o usuário do banco de dados
        return ResponseEntity.noContent().build(); // Retorna resposta 204 (sem conteúdo)
    }
}
