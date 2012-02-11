-- WORD
-- =============================================================================
-- La siguiente función obtiene el próximo id de word para ser insertado.
CREATE OR REPLACE FUNCTION fn_Get_Id_Word (
) RETURNS INTEGER AS $$

    DECLARE
        var_Id_Word               INTEGER         := NULL;

    BEGIN
        var_Id_Word:= NEXTVAL('sq_Word');
        RETURN var_Id_Word;
    END;
$$ LANGUAGE plpgsql;
-- La siguiente función "guarda" una word en la BBDD.
-- Toma como parámetros de entrada los datos del mismo.
-- Entre ellos, el id.
-- Si existe lo actualiza.
-- Si no existe lo crea.
CREATE OR REPLACE FUNCTION fn_Save_Word (
  
    pin_name_Word                  VARCHAR,
    pin_nr                     	   INTEGER,
    pin_max_Tf                     INTEGER
   
  
) RETURNS BOOLEAN AS $$

    DECLARE
        var_flag	           BOOLEAN         := TRUE;
        var_name_Word              VARCHAR         := TRIM( pin_name_Word );
        var_nr                     INTEGER         :=  pin_nr ;  
        var_max_Tf                 INTEGER         :=  pin_max_Tf; 
        var_count                  INTEGER         := 0;
 BEGIN
            -- cuento words
            SELECT COUNT(*)
            INTO var_count
            FROM Word w
            WHERE w.name_Word =  var_name_Word ;
            
                -- veo si existe
            IF (var_count > 0) THEN
            var_flag := false;
           UPDATE Word w SET -- sí existe ==> update
                nr   =  var_nr,
                max_Tf =  var_max_Tf 
             
            WHERE w.name_Word =    var_name_Word ;
        ELSE -- no existe ==> insert
           
            INSERT INTO Word(id_Word, name_Word, nr,max_Tf)
                VALUES ( NEXTVAL('sq_Word'), var_name_Word,var_nr,var_max_Tf);
        END IF;

        RETURN var_flag;
    END;
$$ LANGUAGE plpgsql;
-- La siguiente función elimina una Word de la BBDD.
CREATE OR REPLACE FUNCTION pr_deleteWord (
   pin_name_Word                  VARCHAR
) RETURNS VOID AS $$

    DECLARE   
    var_count                    INTEGER         := 0;
    var_Id_Word                  INTEGER         := NULL;
    BEGIN
            -- Recupero la id de la palabra
            SELECT id_Word
            INTO var_Id_Word  
            FROM  Word w
            WHERE w.name_Word = pin_name_Word;
             -- Cuento postlist del page a eliminar
            SELECT COUNT(*)
            INTO var_count
            FROM  PostList p
            WHERE p.id_Word  =  var_Id_Word  ;

            IF (var_count = 0) THEN -- si no tiene postlist lo elimino
            DELETE FROM  Word w
            WHERE w.name_Word = pin_name_Word;
            END IF;

        RETURN;
    END;
$$ LANGUAGE plpgsql;
-- =============================================================================
-- PostLisT
-- =============================================================================

-- La siguiente función "guarda" una postlist en la BBDD.
-- Toma como parámetros de entrada los datos del mismo.
-- Entre ellos, el id.
-- Si existe lo actualiza.
-- Si no existe lo crea.
CREATE OR REPLACE FUNCTION fn_Save_Postlist (
    pin_name_Word                  VARCHAR,
    pin_url_Name                 VARCHAR,
    pin_frequency                INTEGER
  
   
  
) RETURNS VOID AS $$

    DECLARE
        var_id_Word                INTEGER         :=0;
        var_id_Url                 INTEGER         :=0;
        var_url_Name			   VARCHAR		   := TRIM(pin_url_Name);
		var_name_Word			   VARCHAR		   := TRIM(pin_name_Word);
		var_frequency              INTEGER         := pin_frequency ; 
        var_count                  INTEGER         := 0;		
     
 BEGIN
            -- recupero el id de la word
            SELECT w.id_Word
            INTO  var_id_Word 
            FROM  Word w
            WHERE w.name_Word  = var_name_Word;
            -- recupero el id de la page
			SELECT u.id_Url
            INTO  var_id_Url
            FROM  Page u
            WHERE u.url_Name =  var_url_Name;
                -- veo si existe las word y la page
            IF (var_id_Word  > 0 and var_id_Url  > 0) THEN
             
			 SELECT COUNT(*)
              INTO var_count
              FROM  PostList p
              WHERE p.id_Word  =  var_id_Word and p. id_Url = var_id_Url; 
               -- veo si existe el postlist
              IF (var_count > 0) THEN
              UPDATE PostList p SET -- sí existe ==> update
                frequency = var_frequency   		
               WHERE p.id_Word  =  var_id_Word and p. id_Url = var_id_Url;
              ELSE -- no existe ==> insert
                  INSERT INTO PostList(id_Word, id_Url, frequency)
                  VALUES ( var_id_Word, var_id_Url,var_frequency);
              END IF;
            END IF;

        RETURN ;
    END;
$$ LANGUAGE plpgsql;
-- La siguiente función elimina una postlist de la BBDD.
CREATE OR REPLACE FUNCTION pr_deletePostList(
     pin_name_Word                  VARCHAR,
    pin_url_Name                 VARCHAR
) RETURNS VOID AS $$

    DECLARE
           var_id_Word             INTEGER         :=0;
           var_id_Url              INTEGER         :=0;	
           var_url_Name			   VARCHAR		   := TRIM(  pin_url_Name);
		   var_name_Word		   VARCHAR		   := TRIM(pin_name_Word);
		   var_count               INTEGER         := 0;	
    BEGIN
	       -- recupero el id de la word
            SELECT w.id_Word
            INTO  var_id_Word 
            FROM  Word w
            WHERE w.name_Word  = var_name_Word;
            -- recupero el id de la page
			SELECT u.id_Url
            INTO  var_id_Url
            FROM  Page u
            WHERE u.url_Name =  var_url_Name;
                -- veo si existe las word y la page y elimino
            IF (var_id_Word  > 0 and var_id_Url  > 0) THEN
              DELETE FROM  PostList p
              WHERE p.id_Word  =  var_id_Word and p. id_Url = var_id_Url; 
            END IF;

        RETURN;
    END;
$$ LANGUAGE plpgsql;
-- =============================================================================
-- PAGE
-- =============================================================================
-- La siguiente función obtiene el próximo id de Pae para ser insertado.
CREATE OR REPLACE FUNCTION fn_Get_Id_Page (
) RETURNS INTEGER AS $$

    DECLARE
        var_Id_Url              INTEGER         := NULL;

    BEGIN
        var_Id_Url:= NEXTVAL('sq_Page');
        RETURN var_Id_Url;
    END;
$$ LANGUAGE plpgsql;
-- La siguiente función "guarda" una page en la BBDD.
-- Toma como parámetros de entrada los datos del mismo.
-- Entre ellos, el id.
-- Si existe lo actualiza.
-- Si no existe lo crea.
CREATE OR REPLACE FUNCTION fn_Save_Page (
    pin_id_Url                    INTEGER,
    pin_url_Name                  VARCHAR,
    pin_Modulo                    DECIMAL 

   
  
) RETURNS SMALLINT AS $$

    DECLARE
        var_id_Url                 INTEGER         :=  pin_id_Url;
        var_url_Name               VARCHAR         := TRIM(  pin_url_Name );
        var_Modulo                 DECIMAL         :=  pin_Modulo;  
        var_count                  INTEGER         := 0;
 BEGIN
            -- cuento page
            SELECT COUNT(*)
            INTO var_count
            FROM Page p
            WHERE p.id_Url = var_id_Url;
            
                -- veo si existe la page
            IF (var_count > 0) THEN
            UPDATE Page p SET -- sí existe ==> update
                url_Name  =  var_url_Name,
                Modulo =   var_Modulo
                WHERE p.id_Url = var_id_Url;
        ELSE -- no existe ==> insert
           
            INSERT INTO Page(id_Url,url_Name, Modulo)
                VALUES ( var_id_Url, var_url_Name,var_Modulo );
        END IF;

        RETURN var_id_Url;
    END;
$$ LANGUAGE plpgsql;
-- La siguiente función elimina una page de la BBDD.
CREATE OR REPLACE FUNCTION pr_deletePage (
     pin_id_Url                    INTEGER
) RETURNS VOID AS $$

    DECLARE   
    var_count                    INTEGER         := 0;
    BEGIN
             -- Cuento postlist del page a eliminar
            SELECT COUNT(*)
            INTO var_count
            FROM  PostList p
            WHERE p.id_Url  =  pin_id_Url;

            IF (var_count = 0) THEN -- si no tiene postlist lo elimino
            DELETE FROM  Page u
               WHERE u.id_Url = pin_id_Url;
            END IF;

        RETURN;
    END;
$$ LANGUAGE plpgsql;