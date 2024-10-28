package br.leonardo.receitas.portal_de.receitas.controllers; // Pacote onde a classe está localizada

import org.springframework.stereotype.Controller; // Importa a anotação para indicar que esta classe é um controlador
import org.springframework.web.bind.annotation.GetMapping; // Importa a anotação para mapeamento de requisições GET

@Controller // Indica que esta classe é um controlador de Spring MVC
public class HomeController {

    @GetMapping("/") // Mapeia requisições GET para a raiz da aplicação ("/")
    public String home() {
        return "index"; // Retorna a view da home page, que deve ser o arquivo index.html
    }
}
