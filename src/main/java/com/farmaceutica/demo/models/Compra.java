package com.farmaceutica.demo.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigoCompra;

    @ManyToOne
    private Funcionario responsavel;

    private double valorTotal;

    private boolean status; // true = Em andamento, false = Finalizada

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CompraProduto> produtos = new ArrayList<>();

    @PostPersist
    private void gerarCodigoCompra() {
        this.codigoCompra = String.format("COMP%05d", this.id);
    }

    public void calcularValorTotal() {
        this.valorTotal = produtos.stream()
                .mapToDouble(CompraProduto::getPrecoTotal)
                .sum();
    }

    public Long getId() {
        return id;
    }

    public String getCodigoCompra() {
        return codigoCompra;
    }

    public void setCodigoCompra(String codigoCompra) {
        this.codigoCompra = codigoCompra;
    }

    public Funcionario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Funcionario responsavel) {
        this.responsavel = responsavel;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<CompraProduto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<CompraProduto> produtos) {
        this.produtos = produtos;
    }
}
