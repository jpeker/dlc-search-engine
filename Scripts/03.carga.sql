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
-- =============================================================================
-- POSTLIST
-- =============================================================================
--Añado Primero 2 paginas.
SELECT fn_Get_Id_Page();
SELECT fn_Save_Page(1,'peker.com.ar\index', 5.3);
SELECT fn_Get_Id_Page();
SELECT fn_Save_Page(2,'peker.com.ar\ofertas', 8989.5658);
SELECT fn_Get_Id_Page();
SELECT fn_Save_Page(3,'peker.com.ar\descuentos', 11.1111);
SELECT pr_deletePage(3);


SELECT fn_Save_Postlist('casa','peker.com.ar\index',67);

SELECT fn_Save_Postlist('Julián','peker.com.ar\ofertas',45);

SELECT fn_Save_Postlist('Julián','peker.com.ar\index',38);

SELECT fn_Save_Postlist('Serrano','peker.com.ar\ofertas',11);

SELECT pr_deletePostList('Serrano','peker.com.ar\ofertas');