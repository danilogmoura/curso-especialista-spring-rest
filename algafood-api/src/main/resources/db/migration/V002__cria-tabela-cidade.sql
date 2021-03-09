create table cidade
(
    id          bigserial   not null
        constraint pk_cidade primary key,
    nome_cidade varchar(80) not null,
    nome_estado varchar(80) not null
);