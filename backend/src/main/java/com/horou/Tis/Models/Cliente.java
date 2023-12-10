package com.horou.Tis.Models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;


@Entity
@Table(name = Cliente.TABLE_NAME)
public class Cliente {
    public static final String TABLE_NAME = "cliente";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "emailcliente", length = 45, nullable = false)
    private String email;

    @Column(name = "senhacliente", length = 45, nullable = false)
    private String senha;

    @Column(name = "enderecocliente", length = 80, nullable = true)
    private String endereco;

    @Column(name = "telefonecliente", length = 11, nullable = false)
    private String telefone;

    @Column(name = "datacadastro", nullable = false)
    private LocalDate data;

    @OneToOne
    @JoinColumn(name = "usuario_idusuario", referencedColumnName = "idusuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "cliente")
    private List<Veiculo> veiculos = new ArrayList<Veiculo>();


    public Cliente(String email, String senha, String endereco, Usuario user){
        this.email = email;
        this. senha = senha;
        this.endereco = endereco;
        this.usuario = user;
    }

    public Cliente(){}

    public void setVeiculos(Veiculo v){
        this.veiculos.add(v);
    }

    @JsonIgnore
    public List<Veiculo> getVeiculos(){
        return this.veiculos;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId(){
        return this.id;
    }

    public Integer getUserId(){
        return this.usuario.getId();
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getSenha(){
        return this.senha;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }

    public String getEndereco(){
        return this.endereco;
    }

    public void setEndereco(String endereco){
        this.endereco = endereco;
    }

    public String getTelefone(){
        return this.telefone;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public LocalDate getData(){
        return this.data;
    }

    public void setData(LocalDate data){
        this.data = data;
    }

    public Usuario getUsuario(){
        return this.usuario;
    }

    public void setUsuario(Usuario u){
        this.usuario = u;
    }

}
