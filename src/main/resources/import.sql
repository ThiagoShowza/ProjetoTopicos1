-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

--INSERT ESTADOS
INSERT INTO estado (nome, sigla)
VALUES
  ('Acre', 'AC'),
  ('Alagoas', 'AL'),
  ('Amapá', 'AP'),
  ('Amazonas', 'AM'),
  ('Bahia', 'BA'),
  ('Ceará', 'CE'),
  ('Distrito Federal', 'DF'),
  ('Espírito Santo', 'ES'),
  ('Goiás', 'GO'),
  ('Maranhão', 'MA'),
  ('Mato Grosso', 'MT'),
  ('Mato Grosso do Sul', 'MS'),
  ('Minas Gerais', 'MG'),
  ('Pará', 'PA'),
  ('Paraíba', 'PB'),
  ('Paraná', 'PR'),
  ('Pernambuco', 'PE'),
  ('Piauí', 'PI'),
  ('Rio de Janeiro', 'RJ'),
  ('Rio Grande do Norte', 'RN'),
  ('Rio Grande do Sul', 'RS'),
  ('Rondônia', 'RO'),
  ('Roraima', 'RR'),
  ('Santa Catarina', 'SC'),
  ('São Paulo', 'SP'),
  ('Sergipe', 'SE'),
  ('Tocantins', 'TO');

-- INSERT CIDADES
INSERT INTO cidade (nome, id_estado) VALUES
  ('Rio Branco', 1),
  ('Macapá', 2),
  ('Maceió', 3),
  ('Manaus', 4),
  ('Salvador', 5),
  ('Fortaleza', 6),
  ('Brasília', 7),
  ('Vitória', 8),
  ('Goiânia', 9),
  ('São Luís', 10),
  ('Cuiabá', 11),
  ('Campo Grande', 12),
  ('Belo Horizonte', 13),
  ('Belém', 14),
  ('João Pessoa', 15),
  ('Curitiba', 16),
  ('Recife', 17),
  ('Teresina', 18),
  ('Rio de Janeiro', 19),
  ('Natal', 20),
  ('Porto Alegre', 21),
  ('Porto Velho', 22),
  ('Boa Vista', 23),
  ('Florianópolis', 24),
  ('São Paulo', 25),
  ('Aracaju', 26),
  ('Palmas', 27);


--INSERT DOS ENDERECOS
INSERT INTO public.endereco (cidade_id, bairro, lote, quadra, rua)
VALUES 
  (27, 'Plano diretor Sul', 10, '403Sul', 11),
  (27, 'Plano diretor Norte', 11, '110Norte', 12),
  (27, 'Aureny I', 12, 'Taquaralto', 13),
  (27, 'Aureny II', 13, 'Taquaralto', 14),
  (27, 'Aureny III', 14, 'Taquaralto', 15),
  (25, 'Zona Leste', 15, 'Av. Paulista', 16),
  (25, 'Zona Sul', 16, 'Rua Oscar Freire', 17),
  (25, 'Zona Norte', 17, 'Av. Tiradentes', 18),
  (25, 'Zona Oeste', 18, 'Rua Augusta', 19),
  (1, 'Dinossauro', 01, 'Era', 10);


--INSERT USUARIOS
  INSERT INTO usuario(login, senha, perfil) VALUES --SENHA: "1234"
	( 'maria.user', 'O2JdqlPMBBKPaus+zYDOx/D6Ol9IZk9UFD95DcsTQLBD4euH4P9Sh1OrL4c1l4vLPkYjGgxrMFFUy09ouL7vDA==',1),
	( 'joao.admin', 'O2JdqlPMBBKPaus+zYDOx/D6Ol9IZk9UFD95DcsTQLBD4euH4P9Sh1OrL4c1l4vLPkYjGgxrMFFUy09ouL7vDA==',2),
	( 'amanda.costa', 'O2JdqlPMBBKPaus+zYDOx/D6Ol9IZk9UFD95DcsTQLBD4euH4P9Sh1OrL4c1l4vLPkYjGgxrMFFUy09ouL7vDA==',1),
	( 'pedro.santos', 'O2JdqlPMBBKPaus+zYDOx/D6Ol9IZk9UFD95DcsTQLBD4euH4P9Sh1OrL4c1l4vLPkYjGgxrMFFUy09ouL7vDA==',2),
	( 'luana.almeida', 'O2JdqlPMBBKPaus+zYDOx/D6Ol9IZk9UFD95DcsTQLBD4euH4P9Sh1OrL4c1l4vLPkYjGgxrMFFUy09ouL7vDA==',1),
	( 'carlos.ferreira', 'O2JdqlPMBBKPaus+zYDOx/D6Ol9IZk9UFD95DcsTQLBD4euH4P9Sh1OrL4c1l4vLPkYjGgxrMFFUy09ouL7vDA==',2),
	( 'laura.oliveira', 'O2JdqlPMBBKPaus+zYDOx/D6Ol9IZk9UFD95DcsTQLBD4euH4P9Sh1OrL4c1l4vLPkYjGgxrMFFUy09ouL7vDA==',1),
	( 'ricardo.costa', 'O2JdqlPMBBKPaus+zYDOx/D6Ol9IZk9UFD95DcsTQLBD4euH4P9Sh1OrL4c1l4vLPkYjGgxrMFFUy09ouL7vDA==',2),
	( 'fernanda.santos', 'O2JdqlPMBBKPaus+zYDOx/D6Ol9IZk9UFD95DcsTQLBD4euH4P9Sh1OrL4c1l4vLPkYjGgxrMFFUy09ouL7vDA==',1),
	( 'aline.almeida', 'O2JdqlPMBBKPaus+zYDOx/D6Ol9IZk9UFD95DcsTQLBD4euH4P9Sh1OrL4c1l4vLPkYjGgxrMFFUy09ouL7vDA==',2);


--INSERT PESSOAS
INSERT INTO public.pessoa (data_nascimento, id_endereco, id_usuario, cpf, nome)
VALUES
  ('2000-03-15', 1, 1, '123.456.789-01', 'Maria Silva'),
  ('1995-07-20', 2, 2, '987.654.321-00', 'João Oliveira'),
  ('1988-10-05', 3, 3, '111.222.333-44', 'Amanda Costa'),
  ('1980-12-25', 4, 4, '555.888.999-00', 'Pedro Santos'),
  ('1992-04-18', 5, 5, '777.666.555-44', 'Luana Almeida'),
  ('1990-11-08', 6, 6, '111.222.333-55', 'Carlos Ferreira'),
  ('1985-06-02', 7, 7, '444.555.666-77', 'Laura Oliveira'),
  ('1997-09-12', 8, 8, '888.777.666-11', 'Ricardo Costa'),
  ('1975-08-22', 9, 9, '333.222.111-44', 'Fernanda Santos'),
  ('1994-07-01', 10, 10, '999.888.777-22', 'Aline Almeida');


--INSERT TELEFONES
INSERT INTO public.telefone (id_pessoa, codigoarea, numero)
VALUES 
  (1, 63, '99455-4568'),
  (2, 81, '98765-4321'),
  (3, 55, '99888-7766'),
  (4, 11, '91234-5678'),
  (5, 47, '96666-7777'),
  (6, 21, '95555-1234'),
  (7, 42, '93333-8888'),
  (8, 31, '90000-1111'),
  (9, 48, '97777-5555'),
  (10, 84, '98888-2222');

  --Insert de produtos

  INSERT INTO Produto (id, estoque, nome, preco) VALUES
    (1, 10, 'Produto A', 50.0),
    (2, 15, 'Produto B', 30.0 ),
    (3, 20, 'Produto C', 25.0 ),
    (4, 8, 'Produto D', 60.0),
    (5, 12, 'Produto E', 40.0);