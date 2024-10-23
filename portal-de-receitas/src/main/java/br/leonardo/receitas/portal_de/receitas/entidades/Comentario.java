package br.leonardo.receitas.portal;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String texto;

    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "receita_id")
    private Receita receita;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Construtor com parâmetros
    public Comentario(String texto, Receita receita, Usuario usuario) {
        this.texto = texto;
        this.dataCriacao = LocalDateTime.now(); // Define a data de criação automaticamente
        this.receita = receita;
        this.usuario = usuario;
    }
}
