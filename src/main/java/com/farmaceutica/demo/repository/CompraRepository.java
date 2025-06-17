package com.farmaceutica.demo.repository;

import com.farmaceutica.demo.models.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
    List<Compra> findByCodigoCompraContainingIgnoreCase(String codigo);

    List<Compra> findByStatus(boolean status);

    @Query("SELECT DISTINCT c FROM Compra c JOIN c.produtos p WHERE LOWER(p.nomeProduto) LIKE LOWER(CONCAT('%', :termo, '%'))")
    List<Compra> findByNomeProdutoContaining(@Param("termo") String termo);
}
