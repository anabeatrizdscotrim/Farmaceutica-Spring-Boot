package com.farmaceutica.demo.controllers;

import com.farmaceutica.demo.models.Funcionario;
import com.farmaceutica.demo.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String listarFuncionarios(Model model) {
        model.addAttribute("funcionarios", funcionarioService.listarTodosFuncionarios());
        return "fragments/funcionarios :: content";
    }

    @GetMapping("/filtrar")
    public String filtrar(@RequestParam String termo, Model model) {
        model.addAttribute("funcionarios", funcionarioService.filtrarFuncionarios(termo));
        return "fragments/funcionarios :: content";
    }

    @PostMapping("/salvar")
    @ResponseBody
    public ResponseEntity<Funcionario> salvarFuncionario(@RequestBody Funcionario funcionario) {
        String senhaCriptografada = passwordEncoder.encode(funcionario.getSenha());
        funcionario.setSenha(senhaCriptografada);

        Funcionario salvo = funcionarioService.adicionarFuncionario(funcionario);
        return ResponseEntity.ok(salvo);
    }

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
            existente.setBonificacao(funcionario.getBonificacao());

            return funcionarioService.salvar(existente);
        }
        return null;
    }

    @PostMapping("/remover")
    public String remover(@RequestParam Long id, Model model) {
        funcionarioService.removerFuncionario(id);
        model.addAttribute("funcionarios", funcionarioService.listarTodosFuncionarios());
        return "fragments/funcionarios :: content";
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Map<String, Object>> buscarFuncionario(@PathVariable Long id) {
        Map<String, Object> response = funcionarioService.buscarFuncionarioComBeneficios(id);
        if (response.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

}