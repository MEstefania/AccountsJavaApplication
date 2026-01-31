--Usuario
CREATE table IF NOT EXISTS cuentas.usuario
(
    id              BIGSERIAL primary key,
    username          varchar(150) NOT NULL,
    password           varchar(150) NOT NULL,
    active   boolean
);

ALTER TABLE cuentas.usuario
    OWNER to "tata_owner";

-----GRANTS
GRANT
SELECT,
INSERT
,
UPDATE,
DELETE
ON ALL TABLES IN SCHEMA cuentas
    TO "tata_user";

 ----Crear usuario demo
  INSERT INTO cuentas.usuario VALUES (1,'tata','$2a$10$ReIDNMamo4mwFlDM6sn8V.KCnUNizs5wIyzg5.6.fl/Cwy2Iv7Dkm',true);