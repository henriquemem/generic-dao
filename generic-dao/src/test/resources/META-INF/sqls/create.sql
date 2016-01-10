

create table Funcionario (id bigint not null auto_increment, versao integer, nome varchar(255), primary key (id));
create table Privilegio (id bigint not null auto_increment, versao integer, descricao varchar(255), funcionario_id bigint, usuario_id bigint, primary key (id));
create table Usuario (id bigint not null auto_increment, versao integer, email varchar(255), login varchar(255), nivel varchar(255), senha varchar(255), primary key (id));


create table embalagem (id bigint not null auto_increment,produto_id bigint,nome varchar(50),abreviatura varchar(20),codigo_barras varchar(14),e_embalagem_padrao bit,codigo_ms varchar(20),versao integer, primary key (id));

create table embalagem_codigo_barras (id bigint  not null auto_increment,embalagem_id bigint,codigo_barras varchar(14),principal bit,primary key (id));

create table fabricante (id bigint  not null auto_increment,codigo numeric(8,0),nome varchar(50),abreviatura varchar(20),versao integer,primary key (id));

create table produto (id bigint  not null auto_increment,fabricante_id bigint,codigo numeric(8,0),nome varchar(50),abreviatura varchar(20),versao integer,primary key (id));

