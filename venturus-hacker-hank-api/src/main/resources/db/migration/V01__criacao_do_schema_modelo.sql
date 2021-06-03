CREATE TABLE cliente (
	id integer PRIMARY KEY AUTO_INCREMENT,
	cnpj varchar(255) not null,
	nome varchar(255) not null
);

CREATE TABLE servico (
	id integer PRIMARY KEY AUTO_INCREMENT,
	descricao varchar(255) not null
);

CREATE TABLE contrato (
	id integer PRIMARY KEY AUTO_INCREMENT,
	cliente_id integer not null,
	servico_id integer not null,
	data_inicio timestamp not null,
	data_fim timestamp not null,
	FOREIGN KEY(cliente_id) REFERENCES cliente(id),
	FOREIGN KEY(servico_id) REFERENCES servico(id)
);