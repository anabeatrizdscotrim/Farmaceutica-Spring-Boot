package com.farmaceutica.demo.repository;

import com.farmaceutica.demo.models.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario,Long> {
    List<Funcionario> findBySetor(String setor);

    Optional<Funcionario> findByIdentificacao(String identificacao);

    List<Funcionario> findByNomeContainingIgnoreCaseOrSetorContainingIgnoreCase(String nome, String setor);
}
