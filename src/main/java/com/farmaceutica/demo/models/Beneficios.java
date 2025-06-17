package com.farmaceutica.demo.models;

import jakarta.persistence.*;

@Entity
public class Beneficios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "idFuncionario")
    private Funcionario funcionario;
    private double vTransporte;
    private double vAlimentacao;
    private double vRefeicao;
    private double pSaude;
    private double pOdontologico;

    public Long getId() {
        return id;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public double getvTransporte() {
        return vTransporte;
    }

    public void setvTransporte(double vTransporte) {
        this.vTransporte = vTransporte;
    }

    public double getvAlimentacao() {
        return vAlimentacao;
    }

    public void setvAlimentacao(double vAlimentacao) {
        this.vAlimentacao = vAlimentacao;
    }

    public double getvRefeicao() {
        return vRefeicao;
    }

    public void setvRefeicao(double vRefeicao) {
        this.vRefeicao = vRefeicao;
    }

    public double getpSaude() {
        return pSaude;
    }

    public void setpSaude(double pSaude) {
        this.pSaude = pSaude;
    }

    public double getpOdontologico() {
        return pOdontologico;
    }

    public void setpOdontologico(double pOdontologico) {
        this.pOdontologico = pOdontologico;
    }
}
