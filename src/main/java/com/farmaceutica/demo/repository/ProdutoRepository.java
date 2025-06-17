package com.farmaceutica.demo.repository;

import com.farmaceutica.demo.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Optional<Produto> findByNome(String nome);

    List<Produto> findByCodigoProdutoContainingIgnoreCaseOrNomeContainingIgnoreCase(String codigo, String nome);

    List<Produto> findByStatus(boolean status);
}
