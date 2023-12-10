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
@Table(name = Veiculo.TABLE_NAME)
public class Veiculo {
    public static final String TABLE_NAME = "veiculo";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idveiculo")
    private Integer id;

    @Column(name = "marcaveiculo", length = 15, nullable = false)
    private String marca;

    @Column(name = "modeloveiculo", length = 18, nullable = false)
    private String modelo;

    @Column(name = "placaveiculo", length = 7, nullable = false)
    private String placa;

    @Column(name = "anoveiculo", length = 4, nullable = false)
    private Integer ano;

    @Column(name = "status", nullable = false)
    private int status;

    @ManyToOne
    @JoinColumn(name = "cliente_usuario_idusuario", referencedColumnName = "usuario_idusuario")
    private Cliente cliente;

    public Veiculo(Integer id, String modelo, String marca, String placa, Integer ano, Cliente cliente){
        this.id = id;
        this.modelo = modelo;
        this.marca = marca;
        this.placa = placa;
        this.ano = ano;
        this.cliente = cliente;
    }

    public Veiculo(){}

    public Integer getId(){
        return this.id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getMarca(){
        return this.marca;
    }

    public void setMarca(String marca){
        this.marca = marca;
    }

    public String getModelo(){
        return this.modelo;
    }

    public void setModelo(String modelo){
        this.modelo = modelo;
    }

    public String getPlaca(){
        return this.placa;
    }
    
    public void setPlaca(String placa){
        this.placa = placa;
    }

    public Integer getAno(){
        return this.ano;
    }
    
    public void setAno(Integer ano){
        this.ano = ano;
    }

    public int getStatus(){
        return this.status;
    }

    public void setStatus(int status){
        this.status = status;
    }

    public Cliente getCliente(){
        return this.cliente;
    }

    public void setCliente(Cliente c){
        this.cliente = c;
    }
}
