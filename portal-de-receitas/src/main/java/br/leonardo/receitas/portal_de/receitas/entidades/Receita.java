//Feito Por: 
// Leonardo De Castro Tonon Ra: 10426930
//MATHEUS CALEIRO PINHEIRO RA: 10418688
//JOAO PEDRO FERNANDES MILHOMENS RA: 10417578

package br.leonardo.receitas.portal_de.receitas.entidades;

import jakarta.persistence.*; // Importa as anotações JPA
import lombok.Getter; // Importa a anotação Lombok para gerar getters
import lombok.NoArgsConstructor; // Importa a anotação Lombok para gerar construtor sem parâmetros
import lombok.Setter; // Importa a anotação Lombok para gerar setters
import lombok.ToString; // Importa a anotação Lombok para gerar método toString

import java.util.List; // Importa a classe List

@Entity // Indica que esta classe é uma entidade JPA
@Getter // Gera métodos getters automaticamente
@Setter // Gera métodos setters automaticamente
@NoArgsConstructor // Gera um construtor sem parâmetros
@ToString // Gera o método toString automaticamente
public class Receita {

    @Id // Indica que este campo é a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Geração automática do valor da chave primária
    private Long id; // Identificador da receita

    private String nome; // Nome da receita

    private String descricao; // Descrição da receita

    @OneToMany(mappedBy = "receita", cascade = CascadeType.ALL) // Relacionamento um-para-muitos com Ingrediente
    private List<Ingrediente> ingredientes; // Lista de ingredientes da receita

    @ManyToOne // Relacionamento muitos-para-um com Categoria
    @JoinColumn(name = "categoria_id") // Chave estrangeira que referencia a categoria
    private Categoria categoria; // Categoria associada à receita

    // Construtor com parâmetros
    public Receita(String nome, String descricao, List<Ingrediente> ingredientes, Categoria categoria) {
        this.nome = nome; // Inicializa o nome da receita
        this.descricao = descricao; // Inicializa a descrição da receita
        this.ingredientes = ingredientes; // Inicializa a lista de ingredientes
        this.categoria = categoria; // Inicializa a categoria associada
    }
}
