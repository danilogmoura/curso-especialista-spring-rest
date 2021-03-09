create table estado
(
    id   bigserial   not null
        constraint pk_estado primary key,
    nome varchar(80) not null
);

alter table cidade
    add column estado_id bigint not null,
    add constraint fk_cidade_estado foreign key (estado_id) references estado (id);

alter table cidade
    drop column nome_estado;

alter table cidade
    rename column nome_cidade to cidade;