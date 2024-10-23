package br.leonardo.receitas.portal;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Double quantidade;

    private String unidadeMedida; // Ex: gramas, colheres, etc.

    @ManyToOne
    @JoinColumn(name = "receita_id")
    private Receita receita;

    // Construtor com parâmetros
    public Ingrediente(String nome, Double quantidade, String unidadeMedida, Receita receita) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.unidadeMedida = unidadeMedida;
        this.receita = receita;
    }
}
