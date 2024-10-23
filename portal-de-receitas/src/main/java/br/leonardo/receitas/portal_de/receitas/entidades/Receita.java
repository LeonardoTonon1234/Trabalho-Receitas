package br.leonardo.receitas.portal;

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

    // Construtor com parâmetros
    public Receita(String nome, String descricao, List<Ingrediente> ingredientes) {
        this.nome = nome;
        this.descricao = descricao;
        this.ingredientes = ingredientes;
    }
}
