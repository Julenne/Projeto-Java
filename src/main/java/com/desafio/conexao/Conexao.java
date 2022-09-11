package com.desafio.conexao;

import com.desafio.desafiojava.Cliente;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import com.desafio.desafiojava.Conserto;
import org.hibernate.service.ServiceRegistry;

public class Conexao {
    private static SessionFactory sessao;
    private static final String usuario = "USUARIO POSTGRES";
    private static final String senha = "SENHA POSTGRES";
    private static final String url = "jdbc:postgresql://localhost:PORTA/NOME_BANCO";

    public static SessionFactory getSessao(){
        if(sessao == null) {
            try {
                Configuration configuracao = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER,"org.postgresql.Driver");
                settings.put(Environment.URL, url);
                settings.put(Environment.USER, usuario);
                settings.put(Environment.PASS, senha);
                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "create");

                configuracao.setProperties(settings);
                configuracao.addAnnotatedClass(Cliente.class);
                configuracao.addAnnotatedClass(Conserto.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuracao.getProperties()).build();

                sessao = configuracao.buildSessionFactory(serviceRegistry);
                System.out.println("BANCO DE DADOS CONECTADO COM SUCESSO");
                return sessao;

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return sessao;
    }
    public static void desconectar(){
        sessao.close();
    }

}
