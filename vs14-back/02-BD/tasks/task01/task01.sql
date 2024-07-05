CREATE TABLE VEM_SER.ESTUDANTE (
    id_estudante NUMBER NOT NULL,
    nome VARCHAR2(200) NOT NULL,
    data_nascimento DATE NOT NULL,
    nr_matricula NUMBER(10) NOT NULL,
    ativo CHAR(1) CHECK (ativo IN ('S', 'N')) NOT NULL,
    CONSTRAINT pk_estudante PRIMARY KEY (id_estudante),
    CONSTRAINT uk_nr_matricula UNIQUE (nr_matricula)
);

CREATE SEQUENCE VEM_SER.SEQ_ESTUDANTE START WITH 1 INCREMENT BY 1;

INSERT INTO VEM_SER.ESTUDANTE(id_estudante, nome, data_nascimento, nr_matricula, ativo)
VALUES (SEQ_ESTUDANTE.nextval, 'Pedro Arruda', TO_DATE('03-08-2002', 'dd-mm-yyyy'), 12345601, 'S');

INSERT INTO VEM_SER.ESTUDANTE(id_estudante, nome, data_nascimento, nr_matricula, ativo)
VALUES (SEQ_ESTUDANTE.nextval, 'Tiago Souto', TO_DATE('20-02-2000', 'dd-mm-yyyy'), 12345602, 'S');

INSERT INTO VEM_SER.ESTUDANTE(id_estudante, nome, data_nascimento, nr_matricula, ativo)
VALUES (SEQ_ESTUDANTE.nextval, 'Alisson Fernandes', TO_DATE('15-02-2000', 'dd-mm-yyyy'), 12345603, 'S');

INSERT INTO VEM_SER.ESTUDANTE(id_estudante, nome, data_nascimento, nr_matricula, ativo)
VALUES (SEQ_ESTUDANTE.nextval, 'Juan Pablo', TO_DATE('12-11-1999', 'dd-mm-yyyy'), 12345604, 'N');

INSERT INTO VEM_SER.ESTUDANTE(id_estudante, nome, data_nascimento, nr_matricula, ativo)
VALUES (SEQ_ESTUDANTE.nextval, 'Rafael Isidro', TO_DATE('25-07-2001', 'dd-mm-yyyy'), 12345605, 'S');

INSERT INTO VEM_SER.ESTUDANTE(id_estudante, nome, data_nascimento, nr_matricula, ativo)
VALUES (SEQ_ESTUDANTE.nextval, 'Carlos Eduardo', TO_DATE('10-10-2000', 'dd-mm-yyyy'), 12345606, 'N');

INSERT INTO VEM_SER.ESTUDANTE(id_estudante, nome, data_nascimento, nr_matricula, ativo)
VALUES (SEQ_ESTUDANTE.nextval, 'Rafael Ramos', TO_DATE('22-03-1998', 'dd-mm-yyyy'), 12345607, 'N');

INSERT INTO VEM_SER.ESTUDANTE(id_estudante, nome, data_nascimento, nr_matricula, ativo)
VALUES (SEQ_ESTUDANTE.nextval, 'Rafael Macedo', TO_DATE('22-03-1998', 'dd-mm-yyyy'), 12345608, 'S');

INSERT INTO VEM_SER.ESTUDANTE(id_estudante, nome, data_nascimento, nr_matricula, ativo)
VALUES (SEQ_ESTUDANTE.nextval, 'Davie Gabriel', TO_DATE('15-03-2002', 'dd-mm-yyyy'), 12345609, 'N');

INSERT INTO VEM_SER.ESTUDANTE(id_estudante, nome, data_nascimento, nr_matricula, ativo)
VALUES (SEQ_ESTUDANTE.nextval, 'Ygor Duarte', TO_DATE('22-03-1990', 'dd-mm-yyyy'), 12345610, 'S');

SELECT * FROM VEM_SER.ESTUDANTE;