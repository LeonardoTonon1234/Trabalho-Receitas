package br.leonardo.receitas.portal_de.receitas.entidades;

import jakarta.persistence.*; // Importa as anotações de JPA
import lombok.Getter; // Importa a anotação Lombok para geração de getters
import lombok.NoArgsConstructor; // Importa a anotação Lombok para construtor sem parâmetros
import lombok.Setter; // Importa a anotação Lombok para geração de setters
import lombok.ToString; // Importa a anotação Lombok para geração do método toString

@Entity // Indica que esta classe é uma entidade JPA
@Getter // Gera os métodos getter para os atributos
@Setter // Gera os métodos setter para os atributos
@NoArgsConstructor // Gera um construtor sem parâmetros
@ToString // Gera o método toString
public class Usuario {

    @Id // Indica que este atributo é a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera automaticamente o valor da chave primária
    private Long id; // ID do usuário

    private String nome; // Nome do usuário

    private String email; // Email do usuário

    private String senha; // Senha do usuário

    private boolean isAdmin; // Indica se o usuário é administrador

    public Usuario(String nome, String email, String senha, boolean isAdmin) { // Construtor com parâmetros
        this.nome = nome; // Inicializa o nome
        this.email = email; // Inicializa o email
        this.senha = senha; // Inicializa a senha
        this.isAdmin = isAdmin; // Inicializa o status de administrador
    }
}
