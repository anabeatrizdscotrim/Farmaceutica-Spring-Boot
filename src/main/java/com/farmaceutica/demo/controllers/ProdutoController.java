package com.farmaceutica.demo.controllers;

import com.farmaceutica.demo.models.Produto;
import com.farmaceutica.demo.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("produtos", produtoService.listarTodosProdutos());
        return "produtos/listagem";
    }

    @GetMapping("/novo")
    public String adicionarProduto(Model model) {
        model.addAttribute("produto", new Produto());
        return "produtos/formulario";
    }

    @PostMapping("/salvar")
    public String salvarProduto(@RequestParam String nome,
                                @RequestParam int quantidade,
                                @RequestParam double precoUnitario) {
        produtoService.criarOuAtualizar(nome, quantidade, precoUnitario);
        return "redirect:/produtos";
    }
}
