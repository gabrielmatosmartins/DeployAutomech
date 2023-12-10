package com.horou.Tis.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orcamento")
public class Orcamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idorcamento")
    private Integer idorcamento;

    @Column(name = "valororcamento")
    private double valororcamento;

    @Column(name = "descricaoorcamento", nullable = false, length = 500)
    private String descricaoorcamento;
    
    @Column(name = "statusorcamento")
    private int statusorcamento;

    @Column(name = "dataorcamento")
    private String dataorcamento;

    @ManyToOne
    @JoinColumn(name = "veiculo_idveiculo", nullable = false)
    private Veiculo veiculo;
  

    public Integer getIdorcamento() {
        return this.idorcamento;
    }

    public void setIdorcamento(Integer idorcamento) {
        this.idorcamento = idorcamento;
    }

    public String getDescricaoorcamento() {
        return this.descricaoorcamento;
    }

    public void setDescricaoorcamento(String descricaoorcamento) {
        this.descricaoorcamento = descricaoorcamento;
    }

    public Veiculo getVeiculo() {
        return this.veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public String getDataorcamento() {
        return this.dataorcamento;
    }

    public void setDataorcamento(String dataorcamento) {
        this.dataorcamento = dataorcamento;
    }

    public int getStatusorcamento() {
        return this.statusorcamento;
    }

    public void setStatusorcamento(int statusorcamento) {
        this.statusorcamento = statusorcamento;
    }

    public double getValororcamento() {
        return this.valororcamento;
    }

    public void setValororcamento(double valororcamento) {
        this.valororcamento = valororcamento;
    }

}
