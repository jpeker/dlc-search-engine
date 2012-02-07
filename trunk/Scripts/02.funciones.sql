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
    pin_id_Word                    INTEGER,
    pin_name_Word                  VARCHAR,
    pin_nr                     	   INTEGER,
    pin_max_Tf                     INTEGER
   
  
) RETURNS SMALLINT AS $$

    DECLARE
        var_id_Word                INTEGER         :=  pin_id_Word;
        var_name_Word              VARCHAR         := TRIM( pin_name_Word );
        var_nr                     INTEGER         :=  pin_nr   
        var_max_Tf                 INTEGER         :=  pin_max_Tf 
        var_count                  INTEGER         := 0;
 BEGIN
            -- cuento usuarios
            SELECT COUNT(*)
            INTO var_count
            FROM Word w
            WHERE w.id_Word =  var_id_Word ;
            
                -- veo si existe
            IF (var_count > 0) THEN
            UPDATE Word w SET -- sí existe ==> update
                name_Word  =   var_name_Word,
               nr   =  var_nr,
                max_Tf =  var_max_Tf 
             
            WHERE w.id_Word =     var_id_Word ;
        ELSE -- no existe ==> insert
            IF (var_idusuario IS NULL) THEN
                  var_id_Word  :=fn_Get_Id_Word ();
            END IF;
            INSERT INTO Word(id_Word, name_Word, nr,max_Tf)
                VALUES ( var_id_Word, var_name_Word,var_nr,var_max_Tf);
        END IF;

        RETURN var_idusuario;
    END;
$$ LANGUAGE plpgsql;
-- La siguiente función elimina una Word de la BBDD.
CREATE OR REPLACE FUNCTION pr_deleteWord (
     pin_id_Word                     INTEGER
) RETURNS VOID AS $$

    DECLARE   
    var_count                    INTEGER         := 0;
    BEGIN
             -- Cuento amigos del usuario a eliminar
            SELECT COUNT(*)
            INTO var_count
            FROM  PostList p
            WHERE p.id_Word  =  pin_id_Word ;

            IF (var_count = 0) THEN -- si no tiene amigo lo elimino
            DELETE FROM  Word w
            WHERE w.id_Word = pin_id_Word;
            END IF;

        RETURN;
    END;
$$ LANGUAGE plpgsql;
-- =============================================================================
-- PostLIST
-- =============================================================================

-- La siguiente función "guarda" una postlist en la BBDD.
-- Toma como parámetros de entrada los datos del mismo.
-- Entre ellos, el id.
-- Si existe lo actualiza.
-- Si no existe lo crea.
CREATE OR REPLACE FUNCTION fn_Save_Postlit (
   pin_id_Word                   INTEGER,
    pin_id_Url                   INTEGER,
    pin_frequency                INTEGER
  
   
  
) RETURNS SMALLINT AS $$

    DECLARE
        var_id_Word                INTEGER         :=  pin_id_Word;
        var_id_Url                 INTEGER         := pin_id_Url;
        var_frequency              INTEGER         := pin_frequency ; 
        var_count                  INTEGER         := 0;		
     
 BEGIN
            -- cuento usuarios
            SELECT COUNT(*)
            INTO var_count
            FROM PostList p
            WHERE p.id_Word =  var_id_Word AND p;
            
                -- veo si existe
            IF (var_count > 0) THEN
            UPDATE Word w SET -- sí existe ==> update
                name_Word  =   var_name_Word,
               nr   =  var_nr,
                max_Tf =  var_max_Tf 
             
            WHERE w.id_Word =     var_id_Word ;
        ELSE -- no existe ==> insert
            IF (var_idusuario IS NULL) THEN
                  var_id_Word  :=fn_Get_Id_Word ();
            END IF;
            INSERT INTO Word(id_Word, name_Word, nr,max_Tf)
                VALUES ( var_id_Word, var_name_Word,var_nr,var_max_Tf);
        END IF;

        RETURN var_idusuario;
    END;
$$ LANGUAGE plpgsql;
-- La siguiente función elimina una Word de la BBDD.
CREATE OR REPLACE FUNCTION pr_deleteWord (
     pin_id_Word                     INTEGER
) RETURNS VOID AS $$

    DECLARE   
    var_count                    INTEGER         := 0;
    BEGIN
             -- Cuento amigos del usuario a eliminar
            SELECT COUNT(*)
            INTO var_count
            FROM  PostList p
            WHERE p.id_Word  =  pin_id_Word ;

            IF (var_count = 0) THEN -- si no tiene amigo lo elimino
            DELETE FROM  Word w
            WHERE w.id_Word = pin_id_Word;
            END IF;

        RETURN;
    END;
$$ LANGUAGE plpgsql;
