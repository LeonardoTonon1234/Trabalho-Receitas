package br.leonardo.receitas.portal_de.receitas.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    @OneToMany(mappedBy = "receita", cascade = CascadeType.ALL)
    private List<Ingrediente> ingredientes;

    @ManyToOne // Adiciona a relação com Categoria
    @JoinColumn(name = "categoria_id")
    private Categoria categoria; // Adicionando a referência à categoria

    // Construtor com parâmetros
    public Receita(String nome, String descricao, List<Ingrediente> ingredientes, Categoria categoria) {
        this.nome = nome;
        this.descricao = descricao;
        this.ingredientes = ingredientes;
        this.categoria = categoria; // Atualiza o construtor
    }
}
