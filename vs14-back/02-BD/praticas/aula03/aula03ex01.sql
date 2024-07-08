-- Arualizar o logradouro e complemento com id2 e id3
UPDATE VEM_SER.ENDERECO
SET logradouro = 'Rua das Flores', complemento = 'Apartamento 302'
WHERE id_endereco IN (2, 3);
-- Arualizar o nmr do endereco onde id é 4 para 999999
UPDATE VEM_SER.ENDERECO
SET numero = 999999
WHERE id_endereco = 4;
-- Remover o ultimo endereco da tabela endereco(utilizando a dunção MAX)
DELETE FROM VEM_SER.ENDERECO
WHERE id_endereco = (SELECT MAX(id_endereco) FROM VEM_SER.ENDERECO);
-- Remover o endereco onde o numero é 999999
DELETE FROM VEM_SER.ENDERECO
WHERE numero = 999999;
-- Remover 2 registros da tabela endereco
DELETE FROM VEM_SER.ENDERECO
WHERE ROWNUM <= 2;
