package com.desafio.desafiojava;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="conserto")
public class Conserto {

    @Id
    @Column(name="id_conserto")
    private String idConserto = UUID.randomUUID().toString();

    @Id
    @Column(name="id_clienteconserto")
    private String idCliente;

    @Column(name="tipo_conserto")
    private String tipoConserto;

    @Column(name = "data_atendimento")
    private Date dataAtendimento;

    @Column(name="data_entrega")
    private Date dataEntrega;

    @Column(name="preco")
    private float preco;

    @Column(name = "observacao")
    private String observacao;

    @Column(name="situacao")
    private boolean situacao;

    public Conserto(){

    }
    public Conserto(String idCliente, String tipoConserto, Date dataAtendimento, Date dataEntrega, float preco, String observacao){
        this.idCliente = idCliente;
        this.tipoConserto = tipoConserto;
        this.dataAtendimento = dataAtendimento;
        this.dataEntrega = dataEntrega;
        this.preco = preco;
        this.observacao = observacao;
        this.situacao = false;
    }

    public String getTipoConserto() {
        return tipoConserto;
    }

    public void setTipoConserto(String tipoConserto) {
        this.tipoConserto = tipoConserto;
    }

    public Date getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(Date dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public boolean isSituacao() {
        return situacao;
    }

    public void setSituacao(boolean situacao) {
        this.situacao = situacao;
    }

    public String getIdConserto() {
        return idConserto;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

}
