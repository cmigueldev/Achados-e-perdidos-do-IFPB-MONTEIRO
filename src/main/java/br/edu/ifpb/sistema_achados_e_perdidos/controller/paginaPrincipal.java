package br.edu.ifpb.sistema_achados_e_perdidos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class paginaPrincipal {

    @GetMapping("/")
    public String home(Model model) {
        return "redirect:/item/list";
    }

}
