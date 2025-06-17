package com.farmaceutica.demo.models;

import java.util.List;

public class TransportadoraResponse {
    private Long id;
    private String nome;
    private double frete;
    private List<String> locais;

    public TransportadoraResponse(Transportadora t) {
        this.id = t.getId();
        this.nome = t.getNome();
        this.frete = t.getFrete();
        this.locais = t.getLocais();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getFrete() {
        return frete;
    }

    public void setFrete(double frete) {
        this.frete = frete;
    }

    public List<String> getLocais() {
        return locais;
    }

    public void setLocais(List<String> locais) {
        this.locais = locais;
    }
}
