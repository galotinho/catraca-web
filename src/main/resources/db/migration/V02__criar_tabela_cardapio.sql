CREATE TABLE alimentos (
  id BIGINT AUTO_INCREMENT NOT NULL,
  nome VARCHAR(255) NOT NULL,
  descricao VARCHAR(255),
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
CREATE TABLE refeicao (
  id BIGINT AUTO_INCREMENT NOT NULL,
  data date NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE refeicao_tem_alimentos (
  refeicao_id bigint(20) NOT NULL,
  alimento_id bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE refeicao_tem_alimentos
  ADD KEY FK_REFEICAO_TEM_ALIMENTO_ID (refeicao_id),
  ADD KEY FK_ALIMENTO_TEM_REFEICAO_ID (alimento_id);
  
ALTER TABLE refeicao_tem_alimentos
  ADD CONSTRAINT FK_REFEICAO_TEM_ALIMENTO_ID FOREIGN KEY (refeicao_id) REFERENCES REFEICAO (id),
  ADD CONSTRAINT FK_ALIMENTO_TEM_REFEICAO_ID FOREIGN KEY (alimento_id) REFERENCES ALIMENTOS (id);
 
COMMIT;
 
INSERT INTO alimentos VALUES (1, 'Arroz', 'Cereal');
INSERT INTO alimentos VALUES (2, 'Feijão', 'Cereal');
INSERT INTO alimentos VALUES (3, 'Carne', 'Proteína Animal');
INSERT INTO alimentos VALUES (4, 'Salada', 'Vegetais');

COMMIT;


