CREATE TABLE ALUNO (
  id BIGINT AUTO_INCREMENT NOT NULL,
  nome VARCHAR(255) NOT NULL,
  matricula VARCHAR(255) NOT NULL,
  codigo_cartao VARCHAR(255) NOT NULL,
  id_curso BIGINT(20) NOT NULL,
  id_turma BIGINT(20) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

COMMIT;
