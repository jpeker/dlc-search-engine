-- Database: "SearchEngineDB"

-- DROP DATABASE "SearchEngineDB";

--0CREATE DATABASE "SearchEngineDB"
  --WITH OWNER = postgres
    --   ENCODING = 'UTF8'
     --  TABLESPACE = pg_default
      -- LC_COLLATE = 'Spanish_Argentina.1252'
       --LC_CTYPE = 'Spanish_Argentina.1252'
       --CONNECTION LIMIT = -1;

-- =============================================================================
-- WORD
-- =============================================================================
DROP SEQUENCE IF EXISTS sq_Word CASCADE;
CREATE SEQUENCE sq_Word;
DROP TABLE IF EXISTS Word CASCADE;
CREATE TABLE Word (
  id_Word         	INTEGER                    	NOT NULL,
  name_Word                VARCHAR(32)                     NOT NULL,
  nr              INTEGER                     NOT NULL,
  max_Tf                   INTEGER NOT NULL,
  PRIMARY KEY (id_Word)
);
-- =============================================================================
-- WORD
-- =============================================================================
DROP SEQUENCE IF EXISTS sq_Page CASCADE;
CREATE SEQUENCE sq_Page;
DROP TABLE IF EXISTS Page CASCADE;
CREATE TABLE Page (
  id_Url         	INTEGER                    	NOT NULL,
  url_Name              VARCHAR(32)                     NOT NULL,
  Modulo                DECIMAL 			NOT NULL,
  PRIMARY KEY (id_Url)
);
-- =============================================================================
-- PAGE
-- =============================================================================
DROP SEQUENCE IF EXISTS sq_PostList CASCADE;
CREATE SEQUENCE sq_PostList;
DROP TABLE IF EXISTS PostList CASCADE;
CREATE TABLE PostList (
  id_Word            		  INTEGER                        NOT NULL,
  id_Url            		  INTEGER                        NOT NULL,
  frequency			  INTEGER			 NOT NULL,
 
  PRIMARY KEY (id_Word,id_Url),
   FOREIGN KEY (id_Word)
  REFERENCES Word(id_Word),
   FOREIGN KEY (id_Url)
   REFERENCES Page(id_Url)
);