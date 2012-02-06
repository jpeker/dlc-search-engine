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
--    D A T A B A S E
-- =============================================================================
DROP DATABASE IF EXISTS dlc01db;
CREATE DATABASE dlc01db;
USE dlc01db;

-- =============================================================================
--    T A B L A S ,   Í N D I C E S   Y   S E Q U E N C I A S
-- =============================================================================
-- =============================================================================
-- CLIENTES
-- =============================================================================
DROP TABLE IF EXISTS cliente;
CREATE TABLE cliente (
  idcliente             INTEGER                         NOT NULL AUTO_INCREMENT,
  nombre                VARCHAR(32)                     NOT NULL,
  direccion             VARCHAR(64)                     NOT NULL,
  fechaalta             DATETIME                        NOT NULL,
  fechabaja             DATETIME,
  PRIMARY KEY (idcliente),
  UNIQUE (nombre),
  CHECK ((fechabaja IS NULL) OR (fechaalta < fechabaja))
);

-- =============================================================================
-- PEDIDOS
-- =============================================================================
DROP TABLE IF EXISTS pedido;
CREATE TABLE pedido (
  idpedido              INTEGER                         NOT NULL,
  fecha                 DATE                            NOT NULL,
  detalle               VARCHAR(255)                    NOT NULL,
  idcliente             INTEGER                         NOT NULL,
  entregado             CHAR(1)        DEFAULT 'N'      NOT NULL,
  observaciones         VARCHAR(255),
  PRIMARY KEY (idpedido),
  INDEX (fecha),
  FOREIGN KEY (idcliente)
    REFERENCES cliente(idcliente),
  CHECK (entregado IN ('S', 'N'))
);

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

