-- =============================================================================
--
--    U N I V E R S I D A D   T E C N O L Ó G I C A   N A C I O N A L
--    F A C U L T A D   R E G I O N A L   C Ó R D O B A
--
--    D I S E Ñ O   D E   L E N G U A J E S   D E   C O N S U L T A   ( D L C )
--    P R I M E R   E J E M P L O   -   d l c 0 1 d b :
--                        C L I E N T E S   /   P E D I D O S
--
--    E S T R U C T U R A   B B D D
--    A U T O R :    S c a r a f i a
--
-- =============================================================================
-- =============================================================================
--    T A B L A S ,   Í N D I C E S   Y   S E Q U E N C I A S
-- =============================================================================
-- =============================================================================
-- CLIENTES
-- =============================================================================
DROP SEQUENCE IF EXISTS sq_cliente CASCADE;
CREATE SEQUENCE sq_cliente;
DROP TABLE IF EXISTS cliente CASCADE;
CREATE TABLE cliente (
  idcliente             INTEGER                         NOT NULL,
  nombre                VARCHAR(32)                     NOT NULL,
  direccion             VARCHAR(64)                     NOT NULL,
  fechaalta             TIMESTAMP                       NOT NULL,
  fechabaja             TIMESTAMP,
  PRIMARY KEY (idcliente),
  UNIQUE (nombre),
  CHECK ((fechabaja IS NULL) OR (fechaalta < fechabaja))
);

-- =============================================================================
-- PEDIDOS
-- =============================================================================
DROP SEQUENCE IF EXISTS sq_pedido CASCADE;
CREATE SEQUENCE sq_pedido;
DROP TABLE IF EXISTS pedido CASCADE;
CREATE TABLE pedido (
  idpedido              INTEGER                         NOT NULL,
  fecha                 DATE                            NOT NULL,
  detalle               VARCHAR(255)                    NOT NULL,
  idcliente             INTEGER                         NOT NULL,
  entregado             BOOLEAN        DEFAULT FALSE    NOT NULL,
  observaciones         VARCHAR(255),
  PRIMARY KEY (idpedido),
  FOREIGN KEY (idcliente)
    REFERENCES cliente(idcliente)
);

CREATE INDEX id_pedido_fecha ON pedido(fecha);

-- =============================================================================
--    V I S T A S
-- =============================================================================
-- =============================================================================
-- PEDIDOS
-- =============================================================================
CREATE OR REPLACE VIEW v_pedido AS
  SELECT p.*, c.nombre AS cliente
  FROM pedido p
  JOIN cliente c ON p.idcliente = c.idcliente
;

-- =============================================================================
COMMIT;
-- =============================================================================


