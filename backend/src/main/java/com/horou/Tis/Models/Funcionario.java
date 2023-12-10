package com.horou.Tis.Models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = Funcionario.TABLE_NAME)
public class Funcionario {
    public static final String TABLE_NAME = "funcionario";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Column(name = "salarioFuncionario", nullable = false)
    private Double salario;

    @OneToOne
    @JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario", nullable = false)
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "oficinas_idoficinas", referencedColumnName = "idoficinas", nullable = false)
    private Oficina oficina;

    public Funcionario(Integer id, Double salario, Usuario user, Oficina oficina){
        this.id = id;
        this.salario = salario;
        this.usuario = user;
        this.oficina = oficina;
    }

    public Funcionario(){}

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId(){
        return this.id;
    }

    public void setSalario(double salario){
        this.salario = salario;
    }

    public Double getSalario(){
        return this.salario;
    }

    public void setUsuario(Usuario u){
        this.usuario = u;
    }

    public Usuario getUsuario(){
        return this.usuario;
    }

    public void setOficina(Oficina o){
        this.oficina = o;
    }

    public Oficina getOficina(){
        return this.oficina;
    }

    public Integer getUserId(){
        return this.usuario.getId();
    }
}
