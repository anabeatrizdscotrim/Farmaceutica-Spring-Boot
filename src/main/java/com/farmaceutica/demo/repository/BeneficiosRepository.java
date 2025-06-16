package com.farmaceutica.demo.repository;

import com.farmaceutica.demo.models.Beneficios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiosRepository extends JpaRepository<Beneficios, Long> {
}
