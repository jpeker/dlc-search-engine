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

-- =============================================================================
-- CLIENTES
-- =============================================================================
-- La siguiente función obtiene el próximo id de cliente para ser insertado.
CREATE OR REPLACE FUNCTION fn_getidcliente (
) RETURNS INTEGER AS $$

    DECLARE
        var_idcliente                INTEGER         := NULL;

    BEGIN
        var_idcliente:= NEXTVAL('sq_cliente');
        RETURN var_idcliente;
    END;
$$ LANGUAGE plpgsql;

-- La siguiente función "guarda" un cliente en la BBDD.
-- Toma como parámetros de entrada los datos del mismo.
-- Entre ellos, el id.
-- Si existe lo actualiza.
-- Si no existe lo crea.
CREATE OR REPLACE FUNCTION fn_savecliente (
    pin_idcliente                    INTEGER,
    pin_nombre                       VARCHAR,
    pin_direccion                    VARCHAR,
    pin_fechaalta                    VARCHAR,
    pin_fechabaja                    VARCHAR
  
) RETURNS SMALLINT AS $$

    DECLARE
        var_idcliente                INTEGER         := pin_idcliente;
        var_nombre                   VARCHAR         := TRIM(pin_nombre);
        var_direccion                VARCHAR         := TRIM(pin_direccion);
        var_fechaalta                TIMESTAMP       := NULL;
        var_fechabaja                TIMESTAMP       := NULL;

        var_step                     INTEGER         := 0;

        var_count                    INTEGER         := 0;

    BEGIN
        var_step:= 1; -- convierto fechas
        var_fechaalta := TO_TIMESTAMP(pin_fechaalta, 'YYYYMMDDHH24MISS');
        var_fechabaja := TO_TIMESTAMP(pin_fechabaja, 'YYYYMMDDHH24MISS');

        var_step:= 2; -- cuento clientes
        SELECT COUNT(*)
            INTO var_count
            FROM cliente c
            WHERE c.idcliente = var_idcliente;

        var_step:= 3; -- veo si existe
        IF (var_count > 0) THEN
            var_step:= 4; -- sí existe ==> update
            UPDATE cliente c SET
                nombre = var_nombre,
                direccion = var_direccion,
                fechaalta = var_fechaalta,
                fechabaja = var_fechabaja
            WHERE c.idcliente = var_idcliente;
        ELSE
            var_step:= 5; -- no existe ==> insert
            IF (var_idcliente IS NULL) THEN
                var_idcliente := fn_getidcliente();
            END IF;
            INSERT INTO cliente(idcliente, nombre, direccion, fechaalta, fechabaja)
                VALUES (var_idcliente, var_nombre, var_direccion, var_fechaalta, var_fechabaja);
        END IF;

        RETURN var_idcliente;
    END;
$$ LANGUAGE plpgsql;

-- La siguiente función elimina un cliente de la BBDD.
-- Como se ve, NO devuelve ningún valor (devuelve VOID),
-- por lo que se asemeja más a un stored procedure.
CREATE OR REPLACE FUNCTION pr_deletecliente (
    pin_idcliente                    INTEGER
) RETURNS VOID AS $$

    DECLARE
        var_step                     INTEGER         := 0;

    BEGIN
        var_step:= 1; -- elimino el cliente
        DELETE FROM cliente c
            WHERE c.idcliente = pin_idcliente;

        RETURN;
    END;
$$ LANGUAGE plpgsql;

-- =============================================================================
-- PEDIDOS
-- =============================================================================
-- Completar pedidos

-- =============================================================================
COMMIT;
-- =============================================================================

