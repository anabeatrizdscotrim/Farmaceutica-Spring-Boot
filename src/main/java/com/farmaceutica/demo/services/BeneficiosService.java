package com.farmaceutica.demo.services;

import com.farmaceutica.demo.models.Beneficios;
import com.farmaceutica.demo.models.Funcionario;
import com.farmaceutica.demo.repository.BeneficiosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeneficiosService {
    @Autowired
    BeneficiosRepository beneficiosRepository;

    public void ajustarBeneficiosPorCargo(Funcionario funcionario, Beneficios beneficios) {
        switch (funcionario.getSetor()) {
            case "GF", "GP":
                beneficios.setvRefeicao(500.0);
                beneficios.setvAlimentacao(500.0);
                beneficios.setpSaude(5000.0);
                beneficios.setpOdontologico(5000.0);
                break;
            default:
                beneficios.setvRefeicao(400.0);
                beneficios.setvAlimentacao(400.0);
                beneficios.setpSaude(2000.0);
                beneficios.setpOdontologico(2000.0);
                break;
        }
    }

    public void calcularValeTransporte(Funcionario funcionario, Beneficios beneficios) {
        double vt = funcionario.getSalarioBase() * 0.06;
        beneficios.setvTransporte(vt);
    }

}
