package com.desafio.desafiojava;

import com.desafio.conexao.Conexao;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ClienteDAO {

    /* SAVE METHOD */
    public void saveCliente(Cliente cliente){
        Transaction transaction = null;
        try(Session sessao = Conexao.getSessao().openSession()){
            transaction = sessao.beginTransaction();
            sessao.persist(cliente);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /* UPDATE METHOD */
    public void updateCliente(Cliente cliente){
        Transaction transaction = null;
        try(Session sessao = Conexao.getSessao().openSession()){
            transaction = sessao.beginTransaction();
            sessao.merge(cliente);
            transaction.commit();
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /* DELETE METHOD */
    public void deleteCliente(int id) {
        Transaction transaction = null;
        try (Session sessao = Conexao.getSessao().openSession()) {
            transaction = sessao.beginTransaction();

            Cliente cliente = sessao.get(Cliente.class, id);
            if (cliente != null) {
                sessao.remove(cliente);
                System.out.println("Cliente removido!");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /* GET METHOD */
    public Cliente getCliente(String id){
        Transaction transaction = null;
        Cliente cliente = null;
        try(Session sessao = Conexao.getSessao().openSession()){
            transaction = sessao.beginTransaction();
            cliente = sessao.get(Cliente.class, id);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return cliente;
    }

    /* GET ALL METHOD */
    public List<Cliente> gettAllCliente() {
        List <Cliente> listaCliente = null;
        Transaction transaction = null;

        try(Session sessao = Conexao.getSessao().openSession()){
            CriteriaQuery cq = sessao.getCriteriaBuilder().createQuery(Cliente.class);
            cq.from(Cliente.class);
            transaction = sessao.beginTransaction();
            listaCliente = sessao.createQuery(cq).getResultList();
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listaCliente;
    }
}
