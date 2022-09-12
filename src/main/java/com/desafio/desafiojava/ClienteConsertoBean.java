package com.desafio.desafiojava;

import com.desafio.validacao.ClienteValidacao;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
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

            consertoDAO.saveConserto(conserto);

            conserto = new Conserto();
            cliente = new Cliente();
        } catch (Exception e){
            e.printStackTrace();
        }

    }
    public List<Conserto> listAllConserto(){
        listConserto = consertoDAO.gettAllConserto();

        if(listConserto == null || listConserto.size()==0){
            //mostrar alguma mensagem de erro(não consegui mostrar)
        }
        return listConserto;
    }

    public String retornaSituacao(){
        if(this.conserto.isSituacao()){
            return "Concluido";
        } else {
            return "Não Concluído";
        }
    }

    /*public void validarEmail(FacesContext context, UIComponent comp,
                             Object value){
            if(ClienteValidacao.validaEmail(cliente.getEmailCliente())){
                ((UIInput) comp).setValid(false);
                FacesMessage message = new FacesMessage("errado");
                context.addMessage(comp.getClientId(context), message);
            }
    }*/
    public Cliente clienteConserto(Conserto conserto){
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
        try{
            consertoDAO.deleteConserto(conserto);
        }catch(Exception e){
            e.printStackTrace();
        }
        addMessage("Excluído com sucesso");
    }

    public void addMessage(String summary) { // para mostrar os erros *não funcionou*
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    public Cliente getCliente(){return cliente;}


    public Conserto getConserto(){return conserto;}

    public List<Conserto> getListConserto() {
        return listConserto;
    }

}
