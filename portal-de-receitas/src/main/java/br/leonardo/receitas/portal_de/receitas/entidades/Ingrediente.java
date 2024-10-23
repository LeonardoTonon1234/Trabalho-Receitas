package br.leonardo.receitas.portal_de.receitas.entidades;

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

    private String unidadeMedida;

    @ManyToOne
    @JoinColumn(name = "receita_id")
    private Receita receita;

    public Ingrediente(String nome, Double quantidade, String unidadeMedida, Receita receita) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.unidadeMedida = unidadeMedida;
        this.receita = receita;
    }
}
