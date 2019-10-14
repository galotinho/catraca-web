CREATE TABLE CURSO (
  id BIGINT AUTO_INCREMENT NOT NULL,
  nome VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE TURMA (
  id BIGINT AUTO_INCREMENT NOT NULL,
  nome VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO curso VALUES (1, 'Integrado em Informática');
INSERT INTO curso VALUES (2, 'Integrado em Guia de Turismo');
INSERT INTO curso VALUES (3, 'Subsequente em Agropecuária');
INSERT INTO curso VALUES (4, 'Subsequente em Alimentos');
INSERT INTO curso VALUES (5, 'Subsequente em Agrimensura');
INSERT INTO curso VALUES (6, 'Superior em Agroecologia');
INSERT INTO curso VALUES (7, 'Superior em Gestão em Turismo');
INSERT INTO curso VALUES (8, 'Superior em Engenharia de Alimentos');
INSERT INTO curso VALUES (9, 'EAD em Vendas');

INSERT INTO turma VALUES (1, 'I Semestre');
INSERT INTO turma VALUES (2, 'II Semestre');
INSERT INTO turma VALUES (3, 'III Semestre');
INSERT INTO turma VALUES (4, 'VI Semestre');
INSERT INTO turma VALUES (5, 'V Semestre');
INSERT INTO turma VALUES (6, 'VI Semestre');
INSERT INTO turma VALUES (7, 'VII Semestre');
INSERT INTO turma VALUES (8, 'VIII Semestre');
INSERT INTO turma VALUES (9, 'IX Semestre');
INSERT INTO turma VALUES (10, 'X Semestre');
INSERT INTO turma VALUES (11, '1 Ano');
INSERT INTO turma VALUES (12, '2 Ano');
INSERT INTO turma VALUES (13, '3 Ano');

