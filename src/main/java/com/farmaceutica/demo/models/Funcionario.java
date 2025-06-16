package com.farmaceutica.demo.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFuncionario;
    @Column(unique = true)
    private String identificacao;
    @Column(nullable = false)
    private String senha;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private int idade;
    @Column(nullable = false)
    private String genero;
    @Column(nullable = false)
    private String setor;
    @Column(nullable = false)
    private double salarioBase;
    @Column(nullable = false)
    private double salarioLiquid;
    private double imposto;

    public Funcionario() {
    }

    public Funcionario(String identificacao, String senha, String setor) {
        this.identificacao = identificacao;
        this.senha = senha;
        this.setor = setor;
    }

    //private double folhaPagamento; *Não sei como fazer isso e se tem que ter atributo



    public Long getIdFuncionario() {
        return idFuncionario;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public double getSalarioLiquid() {
        return salarioLiquid;
    }

    public void setSalarioLiquid(double salarioLiquid) {
        this.salarioLiquid = salarioLiquid;
    }

    public double getImposto() {
        return imposto;
    }

    public void setImposto(double imposto) {
        this.imposto = imposto;
    }

}
