CREATE TABLE VEM_SER.PAIS(
    id_pais NUMBER PRIMARY KEY NOT NULL,
    nome VARCHAR2(50) UNIQUE NOT NULL
);

CREATE TABLE VEM_SER.ESTADO(
    id_estado NUMBER PRIMARY KEY NOT NULL,
    id_pais NUMBER NOT NULL,
    nome VARCHAR2(50) UNIQUE NOT NULL,
    CONSTRAINT FK_ESTADO_PAIS FOREIGN KEY ( id_pais ) REFERENCES PAIS( id_pais )
);

CREATE TABLE VEM_SER.CIDADE (
    id_cidade NUMBER PRIMARY KEY NOT NULL,
    id_estado NUMBER NOT NULL,
    nome VARCHAR2(50) UNIQUE NOT NULL,
    CONSTRAINT FK_CIDADE_ESTADO FOREIGN KEY (id_estado) REFERENCES VEM_SER.ESTADO (id_estado)
);

CREATE TABLE VEM_SER.BAIRRO (
    id_bairro NUMBER PRIMARY KEY NOT NULL,
    id_cidade NUMBER NOT NULL,
    nome VARCHAR2(50) NOT NULL,
    CONSTRAINT FK_BAIRRO_CIDADE FOREIGN KEY (id_cidade) REFERENCES VEM_SER.CIDADE (id_cidade)
);

CREATE TABLE VEM_SER.ENDERECO (
    id_endereco NUMBER PRIMARY KEY NOT NULL,
    id_bairro NUMBER NOT NULL,
    logradouro VARCHAR2(100) NOT NULL,
    numero NUMBER NOT NULL,
    complemento VARCHAR2(50),
    cep VARCHAR2(10) NOT NULL,
    CONSTRAINT FK_ENDERECO_BAIRRO FOREIGN KEY (id_bairro) REFERENCES VEM_SER.BAIRRO (id_bairro)
);

CREATE SEQUENCE VEM_SER.seq_pais START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE VEM_SER.seq_estado START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE VEM_SER.seq_cidade START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE VEM_SER.seq_bairro START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE VEM_SER.seq_endereco START WITH 1 INCREMENT BY 1;

INSERT INTO VEM_SER.PAIS (id_pais, nome) VALUES (VEM_SER.seq_pais.NEXTVAL, 'Brasil');
INSERT INTO VEM_SER.PAIS (id_pais, nome) VALUES (VEM_SER.seq_pais.NEXTVAL, 'Argentina');


INSERT INTO VEM_SER.ESTADO (id_estado, id_pais, nome) VALUES (VEM_SER.seq_estado.NEXTVAL, 1, 'Paraíba');
INSERT INTO VEM_SER.ESTADO (id_estado, id_pais, nome) VALUES (VEM_SER.seq_estado.NEXTVAL, 2, 'Buenos Aires');
INSERT INTO VEM_SER.ESTADO (id_estado, id_pais, nome) VALUES (VEM_SER.seq_estado.NEXTVAL, 2, 'Córdoba');


INSERT INTO VEM_SER.CIDADE (id_cidade, id_estado, nome) VALUES (VEM_SER.seq_cidade.NEXTVAL, 1, 'Campina Grande');
INSERT INTO VEM_SER.CIDADE (id_cidade, id_estado, nome) VALUES (VEM_SER.seq_cidade.NEXTVAL, 1, 'João Pessoa');
INSERT INTO VEM_SER.CIDADE (id_cidade, id_estado, nome) VALUES (VEM_SER.seq_cidade.NEXTVAL, 2, 'Buenos Aires');
INSERT INTO VEM_SER.CIDADE (id_cidade, id_estado, nome) VALUES (VEM_SER.seq_cidade.NEXTVAL, 2, 'La Plata');
INSERT INTO VEM_SER.CIDADE (id_cidade, id_estado, nome) VALUES (VEM_SER.seq_cidade.NEXTVAL, 3, 'Córdoba');
INSERT INTO VEM_SER.CIDADE (id_cidade, id_estado, nome) VALUES (VEM_SER.seq_cidade.NEXTVAL, 3, 'Villa Carlos Paz');


INSERT INTO VEM_SER.BAIRRO (id_bairro, id_cidade, nome) VALUES (VEM_SER.seq_bairro.NEXTVAL, 1, 'Centro');
INSERT INTO VEM_SER.BAIRRO (id_bairro, id_cidade, nome) VALUES (VEM_SER.seq_bairro.NEXTVAL, 1, 'Bodocongó');
INSERT INTO VEM_SER.BAIRRO (id_bairro, id_cidade, nome) VALUES (VEM_SER.seq_bairro.NEXTVAL, 2, 'Tambaú');
INSERT INTO VEM_SER.BAIRRO (id_bairro, id_cidade, nome) VALUES (VEM_SER.seq_bairro.NEXTVAL, 2, 'Manaíra');
INSERT INTO VEM_SER.BAIRRO (id_bairro, id_cidade, nome) VALUES (VEM_SER.seq_bairro.NEXTVAL, 3, 'Palermo');
INSERT INTO VEM_SER.BAIRRO (id_bairro, id_cidade, nome) VALUES (VEM_SER.seq_bairro.NEXTVAL, 3, 'Recoleta');
INSERT INTO VEM_SER.BAIRRO (id_bairro, id_cidade, nome) VALUES (VEM_SER.seq_bairro.NEXTVAL, 4, 'Centro');
INSERT INTO VEM_SER.BAIRRO (id_bairro, id_cidade, nome) VALUES (VEM_SER.seq_bairro.NEXTVAL, 4, 'City Bell');
INSERT INTO VEM_SER.BAIRRO (id_bairro, id_cidade, nome) VALUES (VEM_SER.seq_bairro.NEXTVAL, 5, 'Nueva Córdoba');
INSERT INTO VEM_SER.BAIRRO (id_bairro, id_cidade, nome) VALUES (VEM_SER.seq_bairro.NEXTVAL, 5, 'Alberdi');
INSERT INTO VEM_SER.BAIRRO (id_bairro, id_cidade, nome) VALUES (VEM_SER.seq_bairro.NEXTVAL, 6, 'Centro');
INSERT INTO VEM_SER.BAIRRO (id_bairro, id_cidade, nome) VALUES (VEM_SER.seq_bairro.NEXTVAL, 6, 'La Villa');

INSERT INTO VEM_SER.ENDERECO (id_endereco, id_bairro, logradouro, numero, complemento, cep) 
VALUES (VEM_SER.seq_endereco.NEXTVAL, 1, 'Rua Maciel Pinheiro', 123, 'Apto 45', '58100-000');
INSERT INTO VEM_SER.ENDERECO (id_endereco, id_bairro, logradouro, numero, complemento, cep) 
VALUES (VEM_SER.seq_endereco.NEXTVAL, 1, 'Avenida Floriano Peixoto', 999, 'Sala 1001', '58101-001');
INSERT INTO VEM_SER.ENDERECO (id_endereco, id_bairro, logradouro, numero, complemento, cep) 
VALUES (VEM_SER.seq_endereco.NEXTVAL, 2, 'Rua Juvêncio Arruda', 456, '', '58102-002');
INSERT INTO VEM_SER.ENDERECO (id_endereco, id_bairro, logradouro, numero, complemento, cep) 
VALUES (VEM_SER.seq_endereco.NEXTVAL, 2, 'Avenida Assis Chateaubriand', 1234, '', '58103-003');

INSERT INTO VEM_SER.ENDERECO (id_endereco, id_bairro, logradouro, numero, complemento, cep) 
VALUES (VEM_SER.seq_endereco.NEXTVAL, 3, 'Avenida Almirante Tamandaré', 789, 'Apto 101', '58000-000');
INSERT INTO VEM_SER.ENDERECO (id_endereco, id_bairro, logradouro, numero, complemento, cep) 
VALUES (VEM_SER.seq_endereco.NEXTVAL, 3, 'Rua Professora Maria Sales', 123, 'Cobertura', '58001-001');
INSERT INTO VEM_SER.ENDERECO (id_endereco, id_bairro, logradouro, numero, complemento, cep) 
VALUES (VEM_SER.seq_endereco.NEXTVAL, 4, 'Avenida Epitácio Pessoa', 456, 'Apto 102', '58002-002');
INSERT INTO VEM_SER.ENDERECO (id_endereco, id_bairro, logradouro, numero, complemento, cep) 
VALUES (VEM_SER.seq_endereco.NEXTVAL, 4, 'Avenida General Edson Ramalho', 789, 'Casa', '58003-003');


