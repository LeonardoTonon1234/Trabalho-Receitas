//Feito Por: 
// Leonardo De Castro Tonon Ra: 10426930
//MATHEUS CALEIRO PINHEIRO RA: 10418688
//JOAO PEDRO FERNANDES MILHOMENS RA: 10417578

package br.leonardo.receitas.portal_de.receitas.entidades;

import jakarta.persistence.*; // Importa as anotações JPA
import lombok.Getter; // Importa a anotação Lombok para gerar getters
import lombok.NoArgsConstructor; // Importa a anotação Lombok para gerar construtor sem parâmetros
import lombok.Setter; // Importa a anotação Lombok para gerar setters
import lombok.ToString; // Importa a anotação Lombok para gerar método toString

@Entity // Indica que esta classe é uma entidade JPA
@Getter // Gera métodos getters automaticamente
@Setter // Gera métodos setters automaticamente
@NoArgsConstructor // Gera um construtor sem parâmetros
@ToString // Gera o método toString automaticamente
public class Passo {

    @Id // Indica que este campo é a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Geração automática do valor da chave primária
    private Long id; // Identificador do passo

    private String descricao; // Descrição do passo

    private int ordem; // Ordem do passo na receita

    @ManyToOne // Relacionamento muitos-para-um com a entidade Receita
    @JoinColumn(name = "receita_id") // Chave estrangeira que referencia a receita
    private Receita receita; // Receita associada ao passo

    // Construtor com parâmetros
    public Passo(String descricao, int ordem, Receita receita) {
        this.descricao = descricao; // Inicializa a descrição do passo
        this.ordem = ordem; // Inicializa a ordem do passo
        this.receita = receita; // Inicializa a receita associada
    }
}
