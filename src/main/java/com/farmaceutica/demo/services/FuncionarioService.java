package com.farmaceutica.demo.services;

import com.farmaceutica.demo.models.Funcionario;
import com.farmaceutica.demo.repository.FuncionarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class FuncionarioService {
    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Transactional
    public Funcionario adicionarFuncionario(Funcionario funcionario) {
        Funcionario funcionarioSalvo = funcionarioRepository.save(funcionario);

        String identificacao = String.format("EMP%05d", funcionarioSalvo.getIdFuncionario());
        funcionarioSalvo.setIdentificacao(identificacao);

        return funcionarioRepository.save(funcionarioSalvo);
    }

    @Transactional
    public Funcionario atualizarFuncionario(Long idFuncionario, Funcionario funcionarioAtualizado) {
        Funcionario existente = funcionarioRepository.findById(idFuncionario)
                .orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado com ID: " + idFuncionario));


        if (funcionarioAtualizado.getNome() != null && !funcionarioAtualizado.getNome().isEmpty()) {
            existente.setNome(funcionarioAtualizado.getNome());
        }
        if (funcionarioAtualizado.getIdade() != 0) { // Para números, basta verificar se não é nulo
            existente.setIdade(funcionarioAtualizado.getIdade());
        }
        if (funcionarioAtualizado.getGenero() != null && !funcionarioAtualizado.getGenero().isEmpty()) {
            existente.setGenero(funcionarioAtualizado.getGenero());
        }
        if (funcionarioAtualizado.getSetor() != null && !funcionarioAtualizado.getSetor().isEmpty()) {
            existente.setSetor(funcionarioAtualizado.getSetor());
        }
        if (funcionarioAtualizado.getSalarioBase() != 0) {
            existente.setSalarioBase(funcionarioAtualizado.getSalarioBase());
        }
        if (funcionarioAtualizado.getSalarioLiquid() != 0) {
            existente.setSalarioLiquid(funcionarioAtualizado.getSalarioLiquid());
        }


        return funcionarioRepository.save(existente);
    }

    public void removerFuncionario(Long idFuncionario) {
        funcionarioRepository.deleteById(idFuncionario);
    }

    public List<Funcionario> listarFucnionariosPorSetor(String setor) {
        return funcionarioRepository.findBySetor(setor);
    }

    public List<Funcionario> listarTodosFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public Funcionario buscarFuncionarioPorId(Long idFuncionario) {
        return funcionarioRepository.findById(idFuncionario).orElse(null);
    }

    public Funcionario salvar(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public List<Funcionario> filtrarFuncionarios(String termo) {
        return funcionarioRepository.findByNomeContainingIgnoreCaseOrSetorContainingIgnoreCase(termo, termo);
    }

}
