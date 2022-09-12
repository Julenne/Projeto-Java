CREATE DATABASE desafiojava;
CREATE TABLE Cliente(id_cliente VARCHAR(250) NOT NULL,
                     email VARCHAR(255),
                     nome_cliente VARCHAR(255),
                     sobrenome_cliente VARCHAR(255),
                     tel_cliente VARCHAR(255),
                     PRIMARY KEY(id_cliente));
CREATE TABLE Conserto(id_conserto VARCHAR(255) NOT NULL,
                      id_cliente VARCHAR(255) NOT NULL,
                      tipo_conserto VARCHAR(255),
                      observacao VARCHAR(255),
                      preco REAL,
                      data_atendimento DATE,
                      data_entrega DATE,
                      situacao BOOLEAN,
                      PRIMARY KEY(id_conserto,id_cliente),
                      FOREIGN KEY(id_cliente) REFERENCES Cliente(id_cliente));