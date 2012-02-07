-- =============================================================================
-- WORDS
-- =============================================================================
-- Cargo Word 1
SELECT fn_get_id_word();
SELECT fn_save_word(1, 'casa', 3, 4);
-- Cargo Word 2
SELECT fn_get_id_word();
SELECT fn_save_word(2, 'topo', 0,0);
--Cargo Word 3
SELECT fn_get_id_word();
SELECT fn_save_word(3, 'Serrano',3,6);
--Cargo Word 4
SELECT fn_get_id_word();
SELECT fn_save_word(4, 'Julián',2,8);
-- Corrijo nr de Word 1
SELECT fn_save_word(1, 'casa', 1000, 63000);
SELECT pr_deleteWord(2);