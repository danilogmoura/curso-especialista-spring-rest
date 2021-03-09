create table cozinha
(
    id   bigserial   not null
        constraint cozinha_pkey primary key,
    nome varchar(60) not null
);
