package com.desafio.desafiojava;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class ClienteConsertoBean{
    private Cliente cliente = new Cliente();
    private Conserto conserto = new Conserto();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final ConsertoDAO consertoDAO = new ConsertoDAO();

    private List<Conserto> listConserto = new ArrayList<Conserto>();

    public void insertCliente(){
        try{
            clienteDAO.saveCliente(cliente);
            conserto.setIdCliente(cliente.getId());

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

            consertoDAO.saveConserto(conserto);
            //TODO: mudar de pagina quando acabar a inserção
        } catch (Exception e){
            e.printStackTrace();
        }
        conserto = new Conserto();
        cliente = new Cliente();
    }
    public List<Conserto> listAllConserto(){
        listConserto = consertoDAO.gettAllConserto();

        if(listConserto == null || listConserto.size()==0){
            //mostrar alguma mensagem de erro
        }
        return listConserto;
    }

    public Cliente clienteConserto(Conserto conserto){
        System.out.println("id do cliente: "+ conserto.getIdCliente());
        Cliente cliente1 = new Cliente();
        try{

            cliente1 = clienteDAO.getCliente(conserto.getIdCliente());

        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
        }

        return cliente1;
    }

    public String editarConsertoRota(Conserto conserto, Cliente cliente){
        this.conserto = conserto;
        this.cliente = cliente;

        return "editar-conserto.xhtml";
    }
    public void editarConserto(){
        try{
            consertoDAO.updateConserto(this.conserto);
            clienteDAO.updateCliente(this.cliente);
        }catch(Exception e){
            e.printStackTrace();
        }
        conserto = new Conserto();
        cliente = new Cliente();
    }
    public void excluirConserto(Conserto conserto){
        System.out.println("ID CONSERTO: "+conserto.getIdConserto());
        try{

            consertoDAO.deleteConserto(conserto);

        }catch(Exception e){
            e.printStackTrace();
        }
        addMessage("Excluído com sucesso");
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    public Cliente getCliente(){return cliente;}


    public Conserto getConserto(){return conserto;}

    public List<Conserto> getListConserto() {
        return listConserto;
    }

}
