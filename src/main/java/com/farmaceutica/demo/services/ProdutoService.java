package com.farmaceutica.demo.services;

import com.farmaceutica.demo.models.Produto;
import com.farmaceutica.demo.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public void criarOuAtualizar(String nome, int quantidade, double precoUnitario) {
        Produto produto = produtoRepository.findByNome(nome).orElse(null);

        if (produto == null) {
            produto = new Produto();
            produto.setNome(nome);
            produto.setQtdEstoque(quantidade);
            produto.setPrecoCompra(precoUnitario);
        } else {
            produto.setQtdEstoque(produto.getQtdEstoque() + quantidade);
            produto.setPrecoCompra(precoUnitario); // opcional: você pode decidir se atualiza
        }

        produtoRepository.save(produto);
    }

    public List<Produto> listarTodosProdutos() {
        return produtoRepository.findAll();
    }

    public List<Produto> filtrar(String termo, Boolean status) {
        if (termo != null && !termo.isEmpty()) {
            return produtoRepository.findByCodigoProdutoContainingIgnoreCaseOrNomeContainingIgnoreCase(termo, termo);
        } else if (status != null) {
            return produtoRepository.findByStatus(status);
        } else {
            return produtoRepository.findAll();
        }
    }
}
