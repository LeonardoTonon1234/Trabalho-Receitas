//Feito Por: 
// Leonardo De Castro Tonon Ra: 10426930
//MATHEUS CALEIRO PINHEIRO RA: 10418688
//JOAO PEDRO FERNANDES MILHOMENS RA: 10417578

package br.leonardo.receitas.portal_de.receitas; // Pacote onde a classe está localizada

import org.springframework.boot.SpringApplication; // Importa a classe SpringApplication para iniciar a aplicação Spring Boot
import org.springframework.boot.autoconfigure.SpringBootApplication; // Importa a anotação para configuração automática

// Anotação que marca essa classe como uma aplicação Spring Boot
@SpringBootApplication
public class PortalDeReceitasApplication {

    // Método principal que é o ponto de entrada da aplicação
    public static void main(String[] args) {
        // Executa a aplicação Spring Boot
        SpringApplication.run(PortalDeReceitasApplication.class, args);
    }
}
