-- =============================================================================
--
--    U N I V E R S I D A D   T E C N O L Ó G I C A   N A C I O N A L
--    F A C U L T A D   R E G I O N A L   C Ó R D O B A
--
--    D I S E Ñ O   D E   L E N G U A J E S   D E   C O N S U L T A   ( D L C )
--    P R I M E R   E J E M P L O   -   d l c 0 1 d b :
--                        C L I E N T E S   /   P E D I D O S
--
--    C A R G A   B B D D
--    A U T O R :    S c a r a f i a
--
-- =============================================================================
USE dlc01db;

-- =============================================================================
-- CLIENTES
-- =============================================================================
-- Cargo cliente 1
SELECT fn_savecliente(1, 'Cliente 01', 'DirecciOn 01', '2010-01-20 13:45:10', NULL);
-- Cargo cliente 2
SELECT fn_savecliente(2, 'Cliente 02', 'Dirección 02', '2010-02-18 20:30:30', NULL);
-- Corrijo Dirección de cliente 1
SELECT fn_savecliente(1, 'Cliente 01', 'Dirección 01', '2010-01-20 13:45:10', NULL);
-- Doy de baja cliente 2
SELECT fn_savecliente(2, 'Cliente 02', 'Dirección 02', '2010-02-18 20:30:30', '2010-03-05 15:05:20');

--CALL pr_deletecliente(1);

-- =============================================================================
-- PEDIDOS
-- =============================================================================
-- Completar pedidos

-- =============================================================================

