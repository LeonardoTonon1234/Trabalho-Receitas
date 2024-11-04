//Feito Por: 
// Leonardo De Castro Tonon Ra: 10426930
//MATHEUS CALEIRO PINHEIRO RA: 10418688
//JOAO PEDRO FERNANDES MILHOMENS RA: 10417578

package br.leonardo.receitas.portal_de.receitas.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime; // Importa a classe LocalDateTime para a data de criação

@Entity // Indica que esta classe é uma entidade JPA
@Getter // Gera métodos getters automaticamente
@Setter // Gera métodos setters automaticamente
@NoArgsConstructor // Gera um construtor sem parâmetros
@ToString // Gera o método toString automaticamente
public class Comentario {

    @Id // Indica que este campo é a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Geração automática do valor da chave primária
    private Long id; // Identificador do comentário

    private String texto; // Texto do comentário

    private LocalDateTime dataCriacao; // Data e hora de criação do comentário

    @ManyToOne // Relacionamento muitos-para-um com a entidade Receita
    @JoinColumn(name = "receita_id") // Chave estrangeira que referencia a receita
    private Receita receita; // Receita associada ao comentário

    @ManyToOne // Relacionamento muitos-para-um com a entidade Usuario
    @JoinColumn(name = "usuario_id") // Chave estrangeira que referencia o usuário
    private Usuario usuario; // Usuário associado ao comentário

    // Construtor com parâmetros
    public Comentario(String texto, Receita receita, Usuario usuario) {
        this.texto = texto; // Inicializa o texto do comentário
        this.dataCriacao = LocalDateTime.now(); // Define a data de criação como o momento atual
        this.receita = receita; // Inicializa a receita associada
        this.usuario = usuario; // Inicializa o usuário associado
    }
}
