-- Righ Join entre Pessoa e Contato
SELECT *
FROM Contato
RIGHT JOIN Pessoa ON Pessoa.id_pessoa = Contato.id_pessoa;

-- Righ Join entre Pessoa, Pessoa_X_Pessoa_Endereco e Endereco_Pessoa
SELECT *
FROM Pessoa
RIGHT JOIN Pessoa_X_Pessoa_Endereco ON Pessoa.id_pessoa = Pessoa_X_Pessoa_Endereco.id_pessoa
RIGHT JOIN Endereco_Pessoa ON Pessoa_X_Pessoa_Endereco.id_endereco = Endereco_Pessoa.id_endereco;

-- Righ Join entre todas as tabelas (começando por Pessoa)
SELECT *
FROM Pessoa
RIGHT JOIN Contato ON Pessoa.id_pessoa = Contato.id_pessoa
RIGHT JOIN Pessoa_X_Pessoa_Endereco ON Pessoa.id_pessoa = Pessoa_X_Pessoa_Endereco.id_pessoa
RIGHT JOIN Endereco_Pessoa ON Pessoa_X_Pessoa_Endereco.id_endereco = Endereco_Pessoa.id_endereco;

-- Full Join entre Pessoa e Contato
SELECT *
FROM Pessoa
FULL JOIN Contato ON Pessoa.id_pessoa = Contato.id_pessoa;

-- Full Join entre Pessoa, Pessoa_X_Pessoa_Endereco e Endereco_Pessoa
SELECT *
FROM Pessoa
FULL JOIN Pessoa_X_Pessoa_Endereco ON Pessoa.id_pessoa = Pessoa_X_Pessoa_Endereco.id_pessoa
FULL JOIN Endereco_Pessoa ON Pessoa_X_Pessoa_Endereco.id_endereco = Endereco_Pessoa.id_endereco;

-- Full Join entre todas as tabelas (começando por Pessoa)
SELECT *
FROM Pessoa
FULL JOIN Contato ON Pessoa.id_pessoa = Contato.id_pessoa
FULL JOIN Pessoa_X_Pessoa_Endereco ON Pessoa.id_pessoa = Pessoa_X_Pessoa_Endereco.id_pessoa
FULL JOIN Endereco_Pessoa ON Pessoa_X_Pessoa_Endereco.id_endereco = Endereco_Pessoa.id_endereco;

-- Utilizando Exists, selecione as pessoas que têm endereço
SELECT *
FROM Pessoa p
WHERE EXISTS (
    SELECT 1
    FROM Pessoa_X_Pessoa_Endereco pe
    WHERE p.id_pessoa = pe.id_pessoa
);

-- Selecione id, nome da tabela Pessoa junto com id, logradouro da tabela Endereco_Pessoa
SELECT p.id_pessoa, p.nome, e.id_endereco, e.logradouro
FROM Pessoa p
JOIN Pessoa_X_Pessoa_Endereco pe ON p.id_pessoa = pe.id_pessoa
JOIN Endereco_Pessoa e ON pe.id_endereco = e.id_endereco;
