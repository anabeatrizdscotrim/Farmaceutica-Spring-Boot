package com.farmaceutica.demo.controllers;

import com.farmaceutica.demo.security.FuncionarioDetails;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model, Authentication authentication) {
        FuncionarioDetails userDetails = (FuncionarioDetails) authentication.getPrincipal();
        String setor = userDetails.getFuncionario().getSetor();

        model.addAttribute("setor", setor);
        model.addAttribute("nome", userDetails.getUsername());

        return "home";
    }
}
