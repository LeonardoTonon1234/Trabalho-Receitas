package br.leonardo.receitas.portal_de.receitas.controllers;

import br.leonardo.receitas.portal_de.receitas.entidades.Categoria;
import br.leonardo.receitas.portal_de.receitas.repositories.CategoriaRepository;
import br.leonardo.receitas.portal_de.receitas.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indica que esta classe é um controlador REST
@RequestMapping("/api/categorias") // Mapeia requisições para /api/categorias
public class CategoriaController {

    @Autowired // Injeção de dependência do repositório de categorias
    private CategoriaRepository categoriaRepository;

    @GetMapping // Mapeia requisições GET para obter todas as categorias
    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll(); // Retorna todas as categorias do banco de dados
    }

    @PostMapping // Mapeia requisições POST para criar uma nova categoria
    public ResponseEntity<Categoria> createCategoria(@RequestBody Categoria categoria) {
        Categoria savedCategoria = categoriaRepository.save(categoria); // Salva a nova categoria no banco de dados
        return new ResponseEntity<>(savedCategoria, HttpStatus.CREATED); // Retorna a categoria salva com status 201
    }

    @PutMapping("/{id}") // Mapeia requisições PUT para atualizar uma categoria existente
    public ResponseEntity<Categoria> updateCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
        Categoria existingCategoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria not found")); // Lança exceção se a categoria não for encontrada
        existingCategoria.setNome(categoria.getNome()); // Atualiza o nome da categoria
        Categoria updatedCategoria = categoriaRepository.save(existingCategoria); // Salva a categoria atualizada no banco de dados
        return ResponseEntity.ok(updatedCategoria); // Retorna a categoria atualizada
    }

    @DeleteMapping("/{id}") // Mapeia requisições DELETE para excluir uma categoria
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Categoria not found"); // Lança exceção se a categoria não existir
        }
        categoriaRepository.deleteById(id); // Exclui a categoria do banco de dados
        return ResponseEntity.noContent().build(); // Retorna resposta 204 (sem conteúdo)
    }
}
