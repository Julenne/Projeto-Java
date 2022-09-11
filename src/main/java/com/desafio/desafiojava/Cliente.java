package com.desafio.desafiojava;

import jakarta.persistence.*;

import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name="cliente")
public class Cliente {

    @Id
    @Column(name="id_cliente")
    private String id_cliente = UUID.randomUUID().toString();;

    @Column(name="nome_cliente")
    private String nomeCliente;

    @Column(name="sobrenome_cliente")
    private String sobrenomeCliente;

    @Column(name = "email")
    private String emailCliente;

    @Column(name="tel_cliente")
    private String telCliente;

    public Cliente(){

    }

    public Cliente(String nomeCliente, String sobrenomeCliente, String emailCliente, String telCliente){
        this.nomeCliente = nomeCliente;
        this.sobrenomeCliente = sobrenomeCliente;
        this.emailCliente = emailCliente;
        this.telCliente = telCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getSobrenomeCliente() {
        return sobrenomeCliente;
    }

    public void setSobrenomeCliente(String sobrenomeCliente) {
        this.sobrenomeCliente = sobrenomeCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getTelCliente() {
        return telCliente;
    }

    public void setTelCliente(String telCliente) {
        this.telCliente = telCliente;
    }

    public String getId() {
        return id_cliente;
    }

}
