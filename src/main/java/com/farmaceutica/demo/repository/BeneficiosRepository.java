package com.farmaceutica.demo.repository;

import com.farmaceutica.demo.models.Beneficios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BeneficiosRepository extends JpaRepository<Beneficios, Long> {
    Optional<Beneficios> findByFuncionario_IdFuncionario(Long idFuncionario);

}
