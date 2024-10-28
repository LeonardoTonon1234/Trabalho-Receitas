package br.leonardo.receitas.portal_de.receitas.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity // Indica que esta classe é uma entidade JPA
@Getter // Gera métodos getters automaticamente
@Setter // Gera métodos setters automaticamente
@NoArgsConstructor // Gera um construtor sem parâmetros
@ToString // Gera o método toString automaticamente
public class Categoria {

    @Id // Indica que este campo é a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Geração automática do valor da chave primária
    private Long id; // Identificador da categoria

    private String nome; // Nome da categoria

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL) // Relacionamento um-para-muitos com a entidade Receita
    private List<Receita> receitas; // Lista de receitas associadas à categoria

    // Construtor com parâmetros
    public Categoria(String nome, List<Receita> receitas) {
        this.nome = nome; // Inicializa o nome da categoria
        this.receitas = receitas; // Inicializa a lista de receitas
    }
}
