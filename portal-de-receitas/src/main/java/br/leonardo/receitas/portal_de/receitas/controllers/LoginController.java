package br.leonardo.receitas.portal_de.receitas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Indica que este é um controlador para renderizar páginas HTML
public class LoginController {

    @GetMapping("/login") // Mapeia requisições GET para /login
    public String showLoginPage() {
        return "login"; // Renderiza o arquivo login.html (sem a extensão .html)
    }
}
