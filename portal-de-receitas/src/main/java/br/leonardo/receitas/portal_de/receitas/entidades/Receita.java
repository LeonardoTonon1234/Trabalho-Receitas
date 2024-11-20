//Feito Por: 
// Leonardo De Castro Tonon Ra: 10426930
//MATHEUS CALEIRO PINHEIRO RA: 10418688
//JOAO PEDRO FERNANDES MILHOMENS RA: 10417578

package br.leonardo.receitas.portal_de.receitas.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity // Define esta classe como uma entidade JPA
@Getter // Gera automaticamente os métodos getter para os atributos
@Setter // Gera automaticamente os métodos setter para os atributos
@NoArgsConstructor // Gera um construtor sem argumentos
public class Receita {

    @Id // Indica que este campo é a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Geração automática do valor da chave primária
    private Long id; // Identificador único da receita

    @Column(nullable = false) // Define o campo como não nulo
    private String nome; // Nome da receita

    @Column(nullable = false, length = 500) // Define o campo como não nulo e com limite de 500 caracteres
    private String descricao; // Descrição da receita

    @OneToMany(mappedBy = "receita", cascade = CascadeType.ALL, orphanRemoval = true) // Relacionamento de um-para-muitos com Ingrediente
    @JsonIgnore // Ignora este campo na serialização JSON para evitar loops infinitos
    private List<Ingrediente> ingredientes = new ArrayList<>(); // Lista de ingredientes da receita

    @ManyToOne // Relacionamento de muitos-para-um com Categoria
    @JoinColumn(name = "categoria_id") // Define a coluna que referencia a chave primária da tabela Categoria
    private Categoria categoria; // Categoria associada à receita

    @OneToMany(mappedBy = "receita", cascade = CascadeType.ALL, orphanRemoval = true) // Relacionamento de um-para-muitos com Passo
    private List<Passo> passos = new ArrayList<>(); // Lista de passos (modo de preparo) da receita

    @ManyToOne // Relacionamento de muitos-para-um com Usuario
    @JoinColumn(name = "usuario_id", nullable = false) // Define a chave estrangeira para o Usuario
    private Usuario usuario; // Usuario que criou a receita

    @OneToMany(mappedBy = "receita", cascade = CascadeType.ALL, orphanRemoval = true) // Relacionamento de um-para-muitos com Comentário
    @JsonIgnore // Ignora na serialização para evitar loops
    private List<Comentario> comentarios = new ArrayList<>(); // Lista de comentários associados à receita

    @OneToMany(mappedBy = "receita", cascade = CascadeType.ALL, orphanRemoval = true) // Relacionamento de um-para-muitos com Avaliação
    @JsonIgnore // Ignora na serialização para evitar loops
    private List<Avaliacao> avaliacoes = new ArrayList<>(); // Lista de avaliações associadas à receita
}
