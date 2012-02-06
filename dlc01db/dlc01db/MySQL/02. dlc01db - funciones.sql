-- =============================================================================
--
--    U N I V E R S I D A D   T E C N O L Ó G I C A   N A C I O N A L
--    F A C U L T A D   R E G I O N A L   C Ó R D O B A
--
--    D I S E Ñ O   D E   L E N G U A J E S   D E   C O N S U L T A   ( D L C )
--    P R I M E R   E J E M P L O   -   d l c 0 1 d b :
--                        C L I E N T E S   /   P E D I D O S
--
--    F U N C I O N E S / P R O C E D I M I E N T O S   B B D D
--    A U T O R :    S c a r a f i a
--
-- =============================================================================
USE dlc01db;

-- =============================================================================
-- CLIENTES
-- =============================================================================
DROP FUNCTION IF EXISTS fn_savecliente;
CREATE FUNCTION fn_savecliente (
    pin_idcliente                      INTEGER,
    pin_nombre                         VARCHAR(32),
    pin_direccion                      VARCHAR(64),
    pin_fechaalta                      VARCHAR(24),
    pin_fechabaja                      VARCHAR(24)
) RETURNS INTEGER

    BEGIN
        DECLARE var_idcliente          INTEGER;
        DECLARE var_nombre             VARCHAR(32);
        DECLARE var_direccion          VARCHAR(64);
        DECLARE var_fechaalta          DATETIME;
        DECLARE var_fechabaja          DATETIME;

        DECLARE var_step               INTEGER;
        DECLARE var_count              INTEGER;

        SET var_idcliente = pin_idcliente;
        SET var_nombre = TRIM(pin_nombre);
        SET var_direccion = TRIM(pin_direccion);

        SET var_step = 1; -- convierto fechas
        SET var_fechaalta = TIMESTAMP(pin_fechaalta);
        SET var_fechabaja = TIMESTAMP(pin_fechabaja);

        SET var_step = 2; -- cuento clientes
        SELECT COUNT(*)
            INTO var_count
            FROM cliente c
            WHERE c.idcliente = var_idcliente;

        SET var_step = 3; -- veo si existe
        IF (var_count > 0) THEN
            SET var_step = 4; -- sí existe ==> update
            UPDATE cliente c SET
                nombre = var_nombre,
                direccion = var_direccion,
                fechaalta = var_fechaalta,
                fechabaja = var_fechabaja
            WHERE c.idcliente = var_idcliente;
        ELSE
            SET var_step = 5; -- no existe ==> insert
            INSERT INTO cliente(idcliente, nombre, direccion, fechaalta, fechabaja)
                VALUES (var_idcliente, var_nombre, var_direccion, var_fechaalta, var_fechabaja);
            IF (var_idcliente IS NULL) THEN
                SET var_idcliente = LAST_INSERT_ID();
            END IF;
        END IF;

        RETURN var_idcliente;
    END;

DROP PROCEDURE IF EXISTS pr_deletecliente;
CREATE PROCEDURE pr_deletecliente (
    pin_idcliente                      INTEGER
--  IN  pin_idcliente               INTEGER
)

    BEGIN
        DECLARE var_step               INTEGER;

        SET var_step = 1; -- elimino cliente
        DELETE FROM cliente
            WHERE idcliente = pin_idcliente;
    END;

-- =============================================================================
-- PEDIDOS
-- =============================================================================
-- Completar pedidos

-- =============================================================================

