package com.farmaceutica.demo.controllers;

import com.farmaceutica.demo.models.Compra;
import com.farmaceutica.demo.services.CompraService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @GetMapping
    public String listarCompras(Model model) {
        model.addAttribute("compras", compraService.listarTodasCompras());
        return "compras/listagem";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Compra compra, HttpSession session) {
        Long idFuncionario = (Long) session.getAttribute("idFuncionario");

        compraService.salvarCompra(compra, idFuncionario);
        return "redirect:/compras";
    }

    @PostMapping("/finalizar/{id}")
    public String finalizar(@PathVariable Long id) {
        compraService.finalizarCompra(id);
        return "redirect:/compras";
    }
}
