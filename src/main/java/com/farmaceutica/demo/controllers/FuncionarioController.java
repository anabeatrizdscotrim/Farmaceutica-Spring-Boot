package com.farmaceutica.demo.controllers;

import com.farmaceutica.demo.models.Funcionario;
import com.farmaceutica.demo.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    // 🔹 Listar Funcionários (Fragmento Thymeleaf)
    @GetMapping
    public String listarFuncionarios(Model model) {
        model.addAttribute("funcionarios", funcionarioService.listarTodosFuncionarios());
        return "fragments/funcionarios :: content";
    }

    // 🔹 Filtrar Funcionários (Fragmento Thymeleaf)
    @GetMapping("/filtrar")
    public String filtrar(@RequestParam String termo, Model model) {
        model.addAttribute("funcionarios", funcionarioService.filtrarFuncionarios(termo));
        return "fragments/funcionarios :: content";
    }

    // 🔵 Adicionar Funcionário (JSON)
    @PostMapping("/salvar")
    @ResponseBody
    public ResponseEntity<Funcionario> salvarFuncionario(@RequestBody Funcionario funcionario) {
        Funcionario salvo = funcionarioService.adicionarFuncionario(funcionario);
        return ResponseEntity.ok(salvo);
    }

    // 🟡 Editar Funcionário (JSON)
    @PostMapping("/editar")
    @ResponseBody
    public Funcionario editarFuncionario(@RequestBody Funcionario funcionario) {
        Funcionario existente = funcionarioService.buscarFuncionarioPorId(funcionario.getIdFuncionario());

        if (existente != null) {
            existente.setNome(funcionario.getNome());
            existente.setIdade(funcionario.getIdade());
            existente.setGenero(funcionario.getGenero());
            existente.setSetor(funcionario.getSetor());
            existente.setSalarioBase(funcionario.getSalarioBase());
            existente.setSalarioLiquid(funcionario.getSalarioLiquid());

            return funcionarioService.salvar(existente);
        }
        return null;
    }

    // 🔍 Buscar Funcionário por ID (JSON) → Preencher o Modal de Edição
    @GetMapping("/buscar/{id}")
    @ResponseBody
    public Funcionario buscarFuncionario(@PathVariable Long id) {
        return funcionarioService.buscarFuncionarioPorId(id);
    }

    // ❌ Remover Funcionário (Post para segurança)
    @PostMapping("/remover")
    public String remover(@RequestParam Long id, Model model) {
        funcionarioService.removerFuncionario(id);
        model.addAttribute("funcionarios", funcionarioService.listarTodosFuncionarios());
        return "fragments/funcionarios :: content";
    }
}