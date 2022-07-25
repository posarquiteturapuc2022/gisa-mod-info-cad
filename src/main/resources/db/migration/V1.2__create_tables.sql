CREATE TABLE gisamodinfocaddb.usuario (
    id_usuario uuid NOT NULL,
    created_at timestamp,
    update_at timestamp,
    deteled boolean,
    nome varchar(255),
    email varchar(255),
    cnpj varchar(20),
    cpf varchar(15),
    numero_sus varchar(255),
    cidade varchar(255),
    uf varchar(255),
    data_nascimento timestamp,
    password varchar(255),
    PRIMARY KEY (id_usuario)
);

CREATE TABLE gisamodinfocaddb.associado (
    id_associado uuid NOT NULL,
    created_at timestamp,
    update_at timestamp,
    deteled boolean,
    nome_associado varchar(255),
    cpf_associado varchar(255),
    email_associado varchar(255),
	data_nascimento timestamp,
    idade varchar(255),
    codigo_plano varchar(255),
    valor_plano_mensal varchar(255),
    id_tipo_cobertura int4,
    id_tipo_categoria int4,
    odontologico boolean NOT NULL,
    situacao boolean NOT NULL,
    PRIMARY KEY (id_associado)
);

CREATE TABLE gisamodinfocaddb.cirurgia (
   id_cirurgia uuid NOT NULL,
    created_at date,
    update_at date,
    deteled boolean,
    descricao varchar(255),
    nome varchar(255),
    PRIMARY KEY (id_cirurgia)
);

CREATE TABLE gisamodinfocaddb.consulta (
   id_consulta uuid NOT NULL,
    created_at date,
    update_at date,
    deteled boolean,
    descricao varchar(255),
    nome varchar(255),
    PRIMARY KEY (id_consulta)
);

CREATE TABLE gisamodinfocaddb.especialidade (
   id_especialidade uuid NOT NULL,
    created_at date,
    update_at date,
    deteled boolean,
    id_cirurgia uuid,
    id_consulta uuid,
    PRIMARY KEY (id_especialidade)
);

CREATE TABLE gisamodinfocaddb.prestador (
    id_prestador uuid NOT NULL,
    created_at timestamp,
    update_at timestamp,
    deteled boolean,
    cnpj_prestador varchar(255),
    nome_prestador varchar(255),
    email_prestador varchar(255),
    responsavel_prestador varchar(255),
    
    id_tipo_prestador int4,
    id_especialidade uuid,
    
    situacao boolean NOT NULL,
    PRIMARY KEY (id_prestador)
);

alter table if exists gisamodinfocaddb.especialidade 
   add constraint FKpjns7p2ysd10lbcn7muplesq1 
   foreign key (id_cirurgia) 
   references gisamodinfocaddb.cirurgia;

alter table if exists gisamodinfocaddb.especialidade 
   add constraint FKc5gxrsbr74nlx2e1o3qkb8i5g 
   foreign key (id_consulta) 
   references gisamodinfocaddb.consulta; 

alter table if exists gisamodinfocaddb.prestador 
   add constraint FKfxrthhaymi3uqyubh9mayxgja 
   foreign key (id_especialidade) 
   references gisamodinfocaddb.especialidade;
