CREATE DATABASE desafiojava;
CREATE TABLE Cliente(id_cliente SERIAL NOT NULL,
                     email VARCHAR(50) NOT NULL,
                     nome_cliente VARCHAR(50) NOT NULL,
                     sobrenome_cliente VARCHAR(50) NOT NULL,
                     tel_cliente INTEGER NOT NULL,
                     PRIMARY KEY(id_cliente));
CREATE TABLE Conserto(id_conserto SERIAL NOT NULL,
                      id_cliente INTEGER NOT NULL,
                      tipo_conserto VARCHAR(100) NOT NULL,
                      observacao VARCHAR(250) NULL,
                      preco REAL NOT NULL,
                      data_atendimento DATE NOT NULL,
                      data_entrega DATE NOT NULL,
                      situacao BOOLEAN NOT NULL,
                      PRIMARY KEY(id_conserto,id_cliente),
                      FOREIGN KEY(id_cliente) REFERENCES Cliente(id_cliente));