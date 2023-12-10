package com.horou.Tis.Models;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = Reparo.TABLE_NAME)
public class Reparo {

    public static final String TABLE_NAME = "reparo";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idreparo", unique = true)
    private Integer idreparo;

    @Column(name = "valorreparo", nullable = false)
    private Double valorreparo;

    @Column(name = "prazoreparo", nullable = false)
    private LocalDate prazoreparo;

    @Column(name = "datareparo", nullable = false)
    private LocalDate datareparo;

    @Column(name = "imagemreparo")
    private String imagemreparo;

    @Column(name = "descricaoreparo", length = 100, nullable = false)
    private String descricaoreparo;

    @Column(name = "statusreparo", nullable = false)
    private Integer statusreparo;


    public Integer getIdreparo() {
        return this.idreparo;
    }

    public void setIdreparo(Integer idreparo) {
        this.idreparo = idreparo;
    }

    public Double getValorreparo() {
        return this.valorreparo;
    }

    public void setValorreparo(Double valorreparo) {
        this.valorreparo = valorreparo;
    }

    public LocalDate getPrazoreparo() {
        return this.prazoreparo;
    }

    public void setPrazoreparo(LocalDate prazoreparo) {
        this.prazoreparo = prazoreparo;
    }

    public LocalDate getDatareparo() {
        return this.datareparo;
    }

    public void setDatareparo(LocalDate datareparo) {
        this.datareparo = datareparo;
    }

    public String getImagemreparo() {
        return this.imagemreparo;
    }

    public void setImagemreparo(String imagemreparo) {
        this.imagemreparo = imagemreparo;
    }

    public String getDescricaoreparo() {
        return this.descricaoreparo;
    }

    public void setDescricaoreparo(String descricaoreparo) {
        this.descricaoreparo = descricaoreparo;
    }

    public Integer getStatusreparo() {
        return this.statusreparo;
    }

    public void setStatusreparo(Integer statusreparo) {
        this.statusreparo = statusreparo;
    }
    
}