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
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int estrelas;

    @ManyToOne
    @JoinColumn(name = "receita_id")
    private Receita receita;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Avaliacao(int estrelas, Receita receita, Usuario usuario) {
        this.estrelas = estrelas;
        this.receita = receita;
        this.usuario = usuario;
    }
}
