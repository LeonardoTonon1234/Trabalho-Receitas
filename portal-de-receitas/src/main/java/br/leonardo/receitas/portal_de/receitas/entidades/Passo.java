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
public class Passo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private int ordem;

    @ManyToOne
    @JoinColumn(name = "receita_id")
    private Receita receita;

    public Passo(String descricao, int ordem, Receita receita) {
        this.descricao = descricao;
        this.ordem = ordem;
        this.receita = receita;
    }
}
