package com.farmaceutica.demo.controllers;

import com.farmaceutica.demo.models.Transportadora;
import com.farmaceutica.demo.models.TransportadoraResponse;
import com.farmaceutica.demo.services.TransportadoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/transportadoras")
public class TransportadoraController {

    @Autowired
    private TransportadoraService transportadoraService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("transportadoras", transportadoraService.listarTodas());
        return "fragments/transportadoras :: content";
    }

    @GetMapping("/filtrar")
    public String filtrar(@RequestParam String termo, Model model) {
        model.addAttribute("transportadoras", transportadoraService.filtrarPorNome(termo));
        return "fragments/transportadoras :: content";
    }

    @PostMapping("/remover")
    public String remover(@RequestParam Long id, Model model) {
        transportadoraService.remover(id);
        model.addAttribute("transportadoras", transportadoraService.listarTodas());
        return "fragments/transportadoras :: content";
    }

    @GetMapping("/buscar/{id}")
    @ResponseBody
    public TransportadoraResponse buscar(@PathVariable Long id) {
        Transportadora t = transportadoraService.buscarPorId(id);
        return new TransportadoraResponse(t);
    }

    @PostMapping("/salvar")
    @ResponseBody
    public void salvar(@RequestBody Transportadora transportadora) {
        transportadoraService.salvar(transportadora);
    }

    @PostMapping("/editar")
    @ResponseBody
    public void editar(@RequestBody Transportadora transportadora) {
        transportadoraService.salvar(transportadora);
    }
}
