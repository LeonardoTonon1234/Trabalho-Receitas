//Feito Por: 
// Leonardo De Castro Tonon Ra: 10426930
//MATHEUS CALEIRO PINHEIRO RA: 10418688
//JOAO PEDRO FERNANDES MILHOMENS RA: 10417578

package br.leonardo.receitas.portal_de.receitas.exceptions; // Pacote onde a classe está localizada

// Classe personalizada de exceção que estende RuntimeException
public class ResourceNotFoundException extends RuntimeException {
    
    // Construtor que recebe uma mensagem de erro
    public ResourceNotFoundException(String message) {
        super(message); // Chama o construtor da classe pai (RuntimeException) com a mensagem de erro
    }
}
