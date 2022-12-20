insert into usuarios(username, password,enabled,nombre, apellido, email) values('manuonda','123456',1,'david','garcia','manuonda@gmail.com');
insert into usuarios(username, password,enabled,nombre, apellido, email) values('manuonda2','123456',1,'david2','garci2a','manuonda2@gmail.com');

insert into roles(nombre)  values('ROLE_USER');
insert into roles(nombre) values('ROLE_ADMIN');


insert into usuarios_roles(usuario_id, role_id) values(1,1);
insert into usuarios_roles(usuario_id, role_id) values(2,2);
insert into usuarios_roles(usuario_id, role_id) values(2,1);
