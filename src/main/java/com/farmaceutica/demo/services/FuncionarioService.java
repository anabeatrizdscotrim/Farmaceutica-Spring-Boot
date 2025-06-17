package com.farmaceutica.demo.services;

import com.farmaceutica.demo.models.Beneficios;
import com.farmaceutica.demo.models.Funcionario;
import com.farmaceutica.demo.repository.BeneficiosRepository;
import com.farmaceutica.demo.repository.FuncionarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FuncionarioService {
    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    private BeneficiosRepository beneficiosRepository;

    @Autowired
    private BeneficiosService beneficiosService;

    @Transactional
    public Funcionario adicionarFuncionario(Funcionario funcionario) {
        Funcionario funcionarioSalvo = funcionarioRepository.save(funcionario);

        String identificacao = String.format("EMP%05d", funcionarioSalvo.getIdFuncionario());
        funcionarioSalvo.setIdentificacao(identificacao);

        funcionarioSalvo = funcionarioRepository.save(funcionarioSalvo);

        Beneficios beneficios = beneficiosRepository
                .findByFuncionario_IdFuncionario(funcionarioSalvo.getIdFuncionario())
                .orElse(new Beneficios());

        beneficios.setFuncionario(funcionarioSalvo);

        beneficiosService.ajustarBeneficiosPorCargo(funcionarioSalvo, beneficios);
        beneficiosService.calcularValeTransporte(funcionarioSalvo, beneficios);
        processarFinanceiroFuncionario(funcionarioSalvo, beneficios);


        beneficiosRepository.save(beneficios);


        return funcionarioSalvo;
    }

    public void removerFuncionario(Long idFuncionario) {
        Optional<Beneficios> beneficiosOptional = beneficiosRepository.findByFuncionario_IdFuncionario(idFuncionario);
        if (beneficiosOptional.isPresent()) {
            beneficiosRepository.delete(beneficiosOptional.get());
        }

        funcionarioRepository.deleteById(idFuncionario);
    }

    public List<Funcionario> listarTodosFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public Funcionario buscarFuncionarioPorId(Long idFuncionario) {
        return funcionarioRepository.findById(idFuncionario).orElse(null);
    }

    public Funcionario salvar(Funcionario funcionario) {
        Beneficios beneficios = beneficiosRepository
                .findByFuncionario_IdFuncionario(funcionario.getIdFuncionario())
                .orElse(new Beneficios());

        beneficios.setFuncionario(funcionario);

        beneficiosService.ajustarBeneficiosPorCargo(funcionario, beneficios);
        beneficiosService.calcularValeTransporte(funcionario, beneficios);

        processarFinanceiroFuncionario(funcionario, beneficios);

        funcionarioRepository.save(funcionario);
        beneficiosRepository.save(beneficios);
        return funcionario;
    }

    public List<Funcionario> filtrarFuncionarios(String termo) {
        return funcionarioRepository.findByNomeContainingIgnoreCaseOrSetorContainingIgnoreCase(termo, termo);
    }

    public Map<String, Object> buscarFuncionarioComBeneficios(Long id) {
        Optional<Funcionario> funcionarioOpt = funcionarioRepository.findById(id);
        if (funcionarioOpt.isEmpty()) {
            return Collections.emptyMap();
        }

        Funcionario funcionario = funcionarioOpt.get();
        Beneficios beneficios = beneficiosRepository.findByFuncionario_IdFuncionario(id).orElse(null);

        Map<String, Object> response = new HashMap<>();
        response.put("funcionario", funcionario);
        response.put("beneficios", beneficios);

        return response;
    }

    public void processarFinanceiroFuncionario(Funcionario funcionario, Beneficios beneficios) {
        calcularImpostoRenda(funcionario);
        calcularSalarioLiquido(funcionario);
    }

    public void calcularImpostoRenda(Funcionario funcionario) {
        double baseCalculo = funcionario.getSalarioBase() + funcionario.getBonificacao();
        double imposto;

        if (baseCalculo <= 2428.80) {
            imposto = 0;
        } else if (baseCalculo <= 2826.65) {
            imposto = (baseCalculo * 0.075) - 182.16;
        } else if (baseCalculo <= 3751.05) {
            imposto = (baseCalculo * 0.15) - 394.16;
        } else if (baseCalculo <= 4664.68) {
            imposto = (baseCalculo * 0.225) - 675.49;
        } else {
            imposto = (baseCalculo * 0.275) - 908.75;
        }

        funcionario.setImposto(Math.max(imposto, 0));
    }

    public void calcularSalarioLiquido(Funcionario funcionario) {
        double liquido = funcionario.getSalarioBase() + funcionario.getBonificacao() - funcionario.getImposto();
        funcionario.setSalarioLiquid(liquido);
    }


}
