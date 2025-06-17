package com.farmaceutica.demo.services;

import com.farmaceutica.demo.models.Compra;
import com.farmaceutica.demo.models.CompraProduto;
import com.farmaceutica.demo.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private FuncionarioService funcionarioService;

    public List<Compra> listarTodasCompras() {
        return compraRepository.findAll();
    }

    public Compra salvarCompra(Compra compra, Long idFuncionario) {
        compra.setStatus(true); // em andamento

        // seta o responsável pela compra
        compra.setResponsavel(funcionarioService.buscarFuncionarioPorId(idFuncionario));

        // garante que os produtos saibam a qual compra pertencem
        for (CompraProduto cp : compra.getProdutos()) {
            cp.setCompra(compra);
        }

        compra.calcularValorTotal();
        return compraRepository.save(compra);
    }

    public Compra finalizarCompra(Long idCompra) {
        Compra compra = compraRepository.findById(idCompra).orElseThrow();

        if (!compra.isStatus()) {
            throw new IllegalStateException("Compra já está finalizada.");
        }

        for (CompraProduto cp : compra.getProdutos()) {
            produtoService.criarOuAtualizar(
                    cp.getNomeProduto(),
                    cp.getQuantidade(),
                    cp.getPrecoUnitario()
            );
        }

        compra.setStatus(false); // finalizada
        compra.calcularValorTotal();
        return compraRepository.save(compra);
    }

    public List<Compra> filtrar(String codigo, Boolean status, String nomeProduto) {
        if (codigo != null && !codigo.isEmpty()) {
            return compraRepository.findByCodigoCompraContainingIgnoreCase(codigo);
        } else if (nomeProduto != null && !nomeProduto.isEmpty()) {
            return compraRepository.findByNomeProdutoContaining(nomeProduto);
        } else if (status != null) {
            return compraRepository.findByStatus(status);
        } else {
            return compraRepository.findAll();
        }
    }
}
