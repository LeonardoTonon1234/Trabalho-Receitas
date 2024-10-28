package br.leonardo.receitas.portal_de.receitas.entidades;

// Importa as classes necessárias para a entidade Avaliacao
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// Anota a classe como uma entidade JPA
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Avaliacao {

    // Define o ID da avaliação como chave primária e auto-incremental
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Atributo para armazenar a quantidade de estrelas
    private int estrelas;

    // Relacionamento muitos-para-um com Receita
    @ManyToOne
    @JoinColumn(name = "receita_id")
    private Receita receita;

    // Relacionamento muitos-para-um com Usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Construtor com parâmetros para a classe Avaliacao
    public Avaliacao(int estrelas, Receita receita, Usuario usuario) {
        this.estrelas = estrelas;
        this.receita = receita;
        this.usuario = usuario;
    }
}
