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
public class Passo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    private int ordem; // Para definir a sequência dos passos

    @ManyToOne
    @JoinColumn(name = "receita_id")
    private Receita receita;

    // Construtor com parâmetros
    public Passo(String descricao, int ordem, Receita receita) {
        this.descricao = descricao;
        this.ordem = ordem;
        this.receita = receita;
    }
}
