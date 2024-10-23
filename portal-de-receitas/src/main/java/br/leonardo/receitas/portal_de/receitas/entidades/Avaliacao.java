package br.leonardo.receitas.portal.entidades; // Certifique-se de que o pacote corresponde à nova estrutura

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

    private int estrelas; // Avaliação de 1 a 5 estrelas

    @ManyToOne
    @JoinColumn(name = "receita_id")
    private Receita receita;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Construtor com parâmetros
    public Avaliacao(int estrelas, Receita receita, Usuario usuario) {
        this.estrelas = estrelas;
        this.receita = receita;
        this.usuario = usuario;
    }
}
