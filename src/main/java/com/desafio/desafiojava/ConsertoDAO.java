package com.desafio.desafiojava;


import com.desafio.conexao.Conexao;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;


public class ConsertoDAO {

    /* SAVE METHOD */
    public void saveConserto(Conserto conserto){
        Transaction transaction = null;
        try(Session sessao = Conexao.getSessao().openSession()){
            transaction = sessao.beginTransaction();
            sessao.persist(conserto);
            transaction.commit();
        }catch (Exception e){
            if(transaction != null){
            }
            e.printStackTrace();
        }
    }

    /* UPDATE METHOD */
    public void updateConserto(Conserto conserto){
        Transaction transaction = null;
        try(Session sessao = Conexao.getSessao().openSession()){
            transaction = sessao.beginTransaction();
            sessao.merge(conserto);
            transaction.commit();
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /* DELETE METHOD */
    public void deleteConserto(Conserto con) {
        Transaction transaction = null;
        try (Session sessao = Conexao.getSessao().openSession()) {
            transaction = sessao.beginTransaction();
            if (con != null) {
                sessao.remove(con);
                System.out.println("Conserto removido!");
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /* GET METHOD */
        public Conserto getConserto(String id){
            Transaction transaction = null;
            Conserto conserto = null;
            try(Session sessao = Conexao.getSessao().openSession()){
                transaction = sessao.beginTransaction();
                conserto = sessao.get(Conserto.class, id);
                transaction.commit();
            }catch (Exception e){
                if(transaction != null){
                    transaction.rollback();
                }
                e.printStackTrace();
            }
            return conserto;
        }

    /* GET ALL METHOD */
    public List<Conserto> gettAllConserto() {
            List lista = null;
            List<Conserto>listaConserto = null;

            try(Session sessao = Conexao.getSessao().openSession()){
                CriteriaQuery<Conserto> cq = sessao.getCriteriaBuilder().createQuery(Conserto.class);
                Root<Conserto> c = cq.from(Conserto.class);
                cq.select(c);
                listaConserto = sessao.createQuery(cq).getResultList();

            }catch (Exception e){
                e.printStackTrace();
            }
            return listaConserto;
    }
}
