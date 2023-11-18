create sequence hibernate_sequence;

CREATE TABLE colaborador (
	chave varchar(255) NOT NULL,
	nome varchar(255) NOT NULL,
	CONSTRAINT colaborador_pkey PRIMARY KEY (chave)
);

CREATE TABLE participante (
	id int4 NOT NULL,
	email varchar(255) NULL,
	matricula varchar(255) NULL,
	nome varchar(255) NULL,
	validado bool NOT NULL,
	CONSTRAINT participante_pkey PRIMARY KEY (id)
);

CREATE TABLE pergunta (
	id int4 NOT NULL,
	descricao varchar(510) NOT NULL,
	CONSTRAINT pergunta_pkey PRIMARY KEY (id)
);

CREATE TABLE quiz (
	id int4 NOT NULL,
	nome varchar(255) NOT NULL,
	CONSTRAINT quiz_pkey PRIMARY KEY (id)
);

CREATE TABLE participacao (
	id int4 NOT NULL,
	data_fim timestamp NULL,
	data_inicio timestamp NULL,
	participante_id int4 NOT NULL,
	quiz_id int4 NOT NULL,
	CONSTRAINT participacao_pkey PRIMARY KEY (id),
	CONSTRAINT fki4eioa0h7urgojvxon0rb7ohw FOREIGN KEY (quiz_id) REFERENCES quiz(id),
	CONSTRAINT fkl3ibspmr5rsj3n8et0yc6hmv3 FOREIGN KEY (participante_id) REFERENCES participante(id)
);

CREATE TABLE pergunta_opcao (
	id int4 NOT NULL,
	correta bool NOT NULL,
	descricao varchar(510) NOT NULL,
	pergunta_id int4 NOT NULL,
	CONSTRAINT pergunta_opcao_pkey PRIMARY KEY (id),
	CONSTRAINT fk7ap79ubmhabtcnvgy9cofvafk FOREIGN KEY (pergunta_id) REFERENCES pergunta(id)
);

CREATE TABLE quiz_perguntas (
	quiz_id int4 NOT NULL,
	perguntas_id int4 NOT NULL,
	CONSTRAINT quiz_perguntas_pkey PRIMARY KEY (quiz_id, perguntas_id),
	CONSTRAINT fkp43dufpb8kmpku1ae5246xcmm FOREIGN KEY (quiz_id) REFERENCES quiz(id),
	CONSTRAINT fkpy1493eif95dsrrbcih7sqn78 FOREIGN KEY (perguntas_id) REFERENCES pergunta(id)
);

CREATE TABLE participacao_resposta (
	data_resposta timestamp NULL,
	participacao_id int4 NOT NULL,
	pergunta_id int4 NOT NULL,
	opcao_id int4 NOT NULL,
	CONSTRAINT participacao_resposta_pkey PRIMARY KEY (participacao_id, pergunta_id, opcao_id),
	CONSTRAINT fk46ry2qf380d7oeqomty30mcpp FOREIGN KEY (pergunta_id) REFERENCES pergunta(id),
	CONSTRAINT fkbqdlxgeicje0rukllplorr0gf FOREIGN KEY (opcao_id) REFERENCES pergunta_opcao(id),
	CONSTRAINT fkm0vy0iotc1mqafoeg7wx6lpsa FOREIGN KEY (participacao_id) REFERENCES participacao(id)
);