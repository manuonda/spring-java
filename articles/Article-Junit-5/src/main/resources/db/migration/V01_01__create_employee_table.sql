  create table employee (
        id bigserial not null,
        working boolean not null,
        apellido varchar(255),
        email varchar(255),
        nombre varchar(255),
        primary key (id)
    )