create table if not exists partner (
    id uuid primary key,
    name text not null,
    cnpj varchar(18) not null unique
);