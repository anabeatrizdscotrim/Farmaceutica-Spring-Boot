package com.farmaceutica.demo.repository;

import com.farmaceutica.demo.models.Transportadora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransportadoraRepository extends JpaRepository<Transportadora, Long> {

    List<Transportadora> findByNomeContainingIgnoreCase(String nome);
}
