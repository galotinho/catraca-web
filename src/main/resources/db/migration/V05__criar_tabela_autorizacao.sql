CREATE TABLE AUTORIZACAO (
  id BIGINT AUTO_INCREMENT NOT NULL,
  refeicao_id BIGINT(20) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE AUTORIZACAO_TEM_ALUNOS (
  autorizacao_id bigint(20) NOT NULL,
  aluno_id bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE AUTORIZACAO
  ADD KEY FK_REFEICAO_ID (refeicao_id);	

ALTER TABLE AUTORIZACAO
  ADD CONSTRAINT FK_REFEICAO_ID FOREIGN KEY (refeicao_id) REFERENCES refeicao (id);
  
ALTER TABLE AUTORIZACAO_TEM_ALUNOS
  ADD KEY FK_AUTORIZACAO_TEM_ALUNO_ID (autorizacao_id),
  ADD KEY FK_ALUNO_TEM_AUTORIZACAO_ID (aluno_id);
  
ALTER TABLE AUTORIZACAO_TEM_ALUNOS
  ADD CONSTRAINT FK_AUTORIZACAO_TEM_ALUNO_ID FOREIGN KEY (autorizacao_id) REFERENCES autorizacao (id),
  ADD CONSTRAINT FK_ALUNO_TEM_AUTORIZACAO_ID FOREIGN KEY (aluno_id) REFERENCES aluno (id);
  
COMMIT;