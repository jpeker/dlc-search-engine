-- =============================================================================
--
--    U N I V E R S I D A D   T E C N O L Ó G I C A   N A C I O N A L
--    F A C U L T A D   R E G I O N A L   C Ó R D O B A
--
--    D I S E Ñ O   D E   L E N G U A J E S   D E   C O N S U L T A   ( D L C )
--    P R I M E R   E J E M P L O   -   d l c 0 1 d b :
--                        C L I E N T E S   /   P E D I D O S
--
--    C R E A C I Ó N   B B D D
--    A U T O R :    S c a r a f i a
--
-- =============================================================================
-- =============================================================================
--    L E N G U A J E S   M A N E J A D O S   P O R   E L   M O T O R
-- =============================================================================
-- La siguiente sentencia deber ser ejecutada
-- SÓLO 1 (UNA) VEZ por cada BBDD.
CREATE PROCEDURAL LANGUAGE "plpgsql";

-- =============================================================================
--    U S U A R I O S   Y   G R U P O S   ( D E L   M O T O R )
-- =============================================================================
-- El grupo de sentencias NO es necesario para este ejemplo.
-- Mantener comentado.

--CREATE ROLE "grAppClients";
--CREATE ROLE "webApp"
--  LOGIN
--  IN ROLE "grAppClients"
--  INHERIT
--  PASSWORD 'webApp';

-- =============================================================================
COMMIT;
-- =============================================================================


