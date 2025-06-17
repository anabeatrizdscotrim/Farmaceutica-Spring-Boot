package com.farmaceutica.demo.services;

import com.farmaceutica.demo.models.Transportadora;
import com.farmaceutica.demo.repository.TransportadoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransportadoraService {

    @Autowired
    TransportadoraRepository transportadoraRepository;

    public List<Transportadora> listarTodas() {
        return transportadoraRepository.findAll();
    }

    public Transportadora buscarPorId(Long id) {
        return transportadoraRepository.findById(id).orElse(null);
    }

    public void salvar(Transportadora transportadora) {
        transportadoraRepository.save(transportadora);
    }

    public void remover(Long id) {
        transportadoraRepository.deleteById(id);
    }

    public List<Transportadora> filtrarPorNome(String termo) {
        return transportadoraRepository.findByNomeContainingIgnoreCase(termo);
    }
}
