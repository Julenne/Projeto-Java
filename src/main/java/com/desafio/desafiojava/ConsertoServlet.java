package com.desafio.desafiojava;

import com.desafio.conexao.Conexao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/")
public class ConsertoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ConsertoDAO consertoDAO;
    private ClienteDAO clienteDAO;
    private Cliente cliente = new Cliente();
    private Conserto conserto = new Conserto();

    public void init(){
        consertoDAO = new ConsertoDAO();
        clienteDAO = new ClienteDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String acao = request.getServletPath();
        try{
           switch(acao){
               case "/add":
                   showNewForm(request, response);
                   break;
               case "/insert":
                   insertConserto(request, response);
                   break;
               case "/delete":
                  // deleteConserto(request, response);
                   break;
               case "/edit":
                   showEditForm(request, response);
                   break;
               case "/update":
                   updateConserto(request, response);
                   break;
               case"/teste":
                   listConserto(request,response);
                   break;
               default:
                   listConserto(request,response);
                   break;
           }
        }catch (SQLException | ParseException e){
            throw new ServletException(e);
        }
    }
    private void listConserto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List< Conserto > listaConserto = consertoDAO.gettAllConserto();
        request.setAttribute("listUser", listaConserto);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp"); // TODO: adicionar tal página aqui
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("adicionar-conserto.xhtml"); // TODO: adicionar tal página aqui
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String id = request.getParameter("id");
        Conserto conserto = consertoDAO.getConserto(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("editar-conserto.xhtml");// TODO: adicionar tal página aqui
        request.setAttribute("conserto", conserto);
        dispatcher.forward(request, response);
    }

    public ClienteDAO getClienteDAO() {
        return clienteDAO;
    }

    public void setClienteDAO(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public void insertConserto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ParseException, NullPointerException {
        /*String nome = request.getParameter("nome");
        String sobrenome = request.getParameter("sobrenome");
        String email = request.getParameter("email");
        String telefone = request.getParameter("telefone");
        String tipoConserto = request.getParameter("tipo-conserto");
        String dataAtend = request.getParameter("data-atendimento");
        String dataEntg = request.getParameter("data-entrega");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date dataAtendendimento = sdf.parse(dataAtend);
        Date dataEntrega = sdf.parse(dataEntg);
        Float preco = Float.valueOf(request.getParameter("preco"));
        String observacao = request.getParameter("observacao");*/

        /*Cliente cliente = new Cliente(nome, sobrenome, email, telefone);*/

        clienteDAO.saveCliente(cliente);//TODO:Fazer uma verificação se o cliente ja está cadastrado ou ver alguma solução.
        System.out.println();

        /*Conserto conserto = new Conserto(cliente.getId(), tipoConserto,dataAtendendimento,dataEntrega,preco,observacao);*/
        System.out.println("Preço do negocio: "+conserto.getPreco());
        consertoDAO.saveConserto(conserto);

        response.sendRedirect("index.jsp"); //TODO: mudar isso aqui depois para a pagina principal.
    }
    private void updateConserto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ParseException {
        String nome = request.getParameter("nome-cliente");
        String sobrenome = request.getParameter("sobrenome-cliente");
        String email = request.getParameter("email");
        String telefone = request.getParameter("telefone");
        String tipoConserto = request.getParameter("tipo-conserto");
        String dataAtend = request.getParameter("data-atendimento");
        String dataEntg = request.getParameter("data-entrega");
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date dataAtendendimento = sdf.parse(dataAtend);
        Date dataEntrega = sdf.parse(dataEntg);
        Float preco = Float.valueOf(request.getParameter("preco"));
        String observacao = request.getParameter("observacao");

        Cliente cliente = new Cliente(nome, sobrenome, email, telefone);
        clienteDAO.getCliente(cliente.getId());
        Conserto conserto = new Conserto(cliente.getId(),tipoConserto,dataAtendendimento,dataEntrega,preco,observacao);

        consertoDAO.updateConserto(conserto);
        response.sendRedirect("index.jsp"); //TODO: mudar isso aqui depois para a pagina principal.
    }
   /* private void deleteConserto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String id = request.getParameter("id");//TODO: ver se é isso mesmo
        consertoDAO.deleteConserto(id);
        response.sendRedirect("index.jsp");//TODO: mudar isso aqui depois para a pagina principal.
    }*/
}
