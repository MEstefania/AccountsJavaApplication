CREATE SCHEMA if not exists cuentas;
ALTER SCHEMA cuentas OWNER TO "tata_owner";

--Cuenta
CREATE table IF NOT EXISTS cuentas.cuenta
(
    id_cue              BIGSERIAL primary key,
    numero_cue          text NOT NULL,
    tipo_cue            varchar(15) NOT NULL,
    saldo_inicial_cue   numeric(19, 2) NOT NULL,
    estado_cue          boolean NOT NULL,
    id_cli              bigint
);

ALTER TABLE cuentas.cuenta
    OWNER to "tata_owner";

--Movimiento
CREATE table IF NOT EXISTS cuentas.movimiento
(
    id_mov          BIGSERIAL primary key,
    fecha_mov       timestamp NOT NULL,
    tipo_mov        varchar(15) NOT NULL,
    valor_mov       numeric(19, 2) NOT NULL,
    saldo_mov       numeric(19, 2) NOT NULL,
    id_cue          bigint
                    constraint fk_movimiento_cuenta_id
                    references cuentas.cuenta(id_cue)
);

ALTER TABLE cuentas.movimiento
    OWNER to "tata_owner";

-----GRANTS
GRANT
USAGE
ON
SCHEMA
cuentas TO "tata_user";

GRANT
SELECT,
INSERT
,
UPDATE,
DELETE
ON ALL TABLES IN SCHEMA cuentas
    TO "tata_user";

GRANT
SELECT,
UPDATE
    ON ALL
    SEQUENCES IN SCHEMA cuentas
    TO "tata_user";

GRANT
SELECT,
INSERT,
UPDATE,
DELETE
ON ALL TABLES IN SCHEMA public
    TO "tata_user";