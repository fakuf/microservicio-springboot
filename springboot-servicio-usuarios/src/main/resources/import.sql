INSERT INTO `USUARIOS` (username, password, habilitado, nombre, apellido, email) VALUES ('Facundo', '$2a$10$6cIvhBF28VGvh3WwIQa40ubj70A1O.XRrLm/QBefWvwE55HUvs/o6', 1, 'Facundo', 'Falcone', 'facu@gmail.com')
INSERT INTO `USUARIOS` (username, password, habilitado, nombre, apellido, email) VALUES ('Nicolas', '$2a$10$LhxYFCcoJvOcgwNJL1PTxuvQpYJXcuqJTZ5T8zyA6XPIAwyUcJ/7i', 1, 'Nicolas', 'Nicolas', 'nico@gmail.com')

INSERT INTO `ROLES` (nombre) VALUES ('ROLE_USER');
INSERT INTO `ROLES` (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO `USUARIOS_ROLES` (usuario_id, rol_id) VALUES ('1', '1');
INSERT INTO `USUARIOS_ROLES` (usuario_id, rol_id) VALUES ('2', '2');
INSERT INTO `USUARIOS_ROLES` (usuario_id, rol_id) VALUES ('2', '1');