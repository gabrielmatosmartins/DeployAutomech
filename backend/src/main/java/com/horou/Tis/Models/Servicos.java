package com.horou.Tis.Models;

import java.time.LocalDate;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "servico")
public class Servicos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idservico")
    private Integer idServico;

    @Column(name = "valorServico")
    private double valorServico;

    @Column(name = "dataServico")
    private LocalDate dataServico;

    @Column(name = "descricaoServico")
    private String descricaoServico;

    @Column(name = "obsServico")
    private String obsServico;

    @Column(name ="prazoServico")
    private LocalDate prazoServico;


    public Integer getId() {
        return this.idServico;
    }

    public void setId(Integer id) {
        this.idServico = id;
    }

    public double getValorServico() {
        return this.valorServico;
    }

    public void setValorServico(double valorServico) {
        this.valorServico = valorServico;
    }

    public LocalDate getDataServico() {
        return this.dataServico;
    }

    public void setDataServico(LocalDate dataServico) {
        this.dataServico = dataServico;
    }

    public String getDescricaoServico() {
        return this.descricaoServico;
    }

    public void setDescricaoServico(String descricaoServico) {
        this.descricaoServico = descricaoServico;
    }

    public String getObsServico() {
        return this.obsServico;
    }

    public void setObsServico(String obsServico) {
        this.obsServico = obsServico;
    }

    public LocalDate getPrazoServico() {
        return this.prazoServico;
    }

    public void setPrazoServico(LocalDate prazoServico) {
        this.prazoServico = prazoServico;
    }



    
}
