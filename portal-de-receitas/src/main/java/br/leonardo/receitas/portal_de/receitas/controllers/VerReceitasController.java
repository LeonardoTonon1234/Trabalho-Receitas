//Feito Por:
// Leonardo De Castro Tonon Ra: 10426930
// MATHEUS CALEIRO PINHEIRO RA: 10418688
// JOAO PEDRO FERNANDES MILHOMENS RA: 10417578

package br.leonardo.receitas.portal_de.receitas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Indica que este é um controlador para renderizar páginas HTML
public class VerReceitasController {

    @GetMapping("/ver-receitas") // Mapeia o endpoint para abrir a página ver-receitas.html
    public String showVerReceitasPage() {
        return "ver-receitas"; // Nome do arquivo HTML na pasta templates (sem extensão .html)
    }
}
