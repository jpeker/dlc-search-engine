--
-- PostgreSQL database dump
--

-- Dumped from database version 9.0.4
-- Dumped by pg_dump version 9.0.4
-- Started on 2012-02-03 19:04:10

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- TOC entry 314 (class 2612 OID 11574)
-- Name: plpgsql; Type: PROCEDURAL LANGUAGE; Schema: -; Owner: postgres
--

CREATE OR REPLACE PROCEDURAL LANGUAGE plpgsql;


ALTER PROCEDURAL LANGUAGE plpgsql OWNER TO postgres;

SET search_path = public, pg_catalog;

--
-- TOC entry 18 (class 1255 OID 16506)
-- Dependencies: 314 5
-- Name: increment(integer); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION increment(i integer) RETURNS integer
    LANGUAGE plpgsql
    AS $$BEGIN
RETURN i + 1;
END;$$;


ALTER FUNCTION public.increment(i integer) OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1507 (class 1259 OID 16488)
-- Dependencies: 5
-- Name: Page; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE "Page" (
    "id_Url" integer NOT NULL,
    "id_Url_Base" integer,
    url text NOT NULL,
    module double precision
);


ALTER TABLE public."Page" OWNER TO postgres;

--
-- TOC entry 1508 (class 1259 OID 16501)
-- Dependencies: 5
-- Name: PostList; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE "PostList" (
    "id_Url" integer NOT NULL,
    "id_Word" integer NOT NULL,
    "term_Frecuency" integer
);


ALTER TABLE public."PostList" OWNER TO postgres;

--
-- TOC entry 1506 (class 1259 OID 16472)
-- Dependencies: 5
-- Name: Word; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE "Word" (
    word text NOT NULL,
    nr integer,
    "max_Tf" integer,
    "id_Word" integer NOT NULL
);


ALTER TABLE public."Word" OWNER TO postgres;

--
-- TOC entry 1794 (class 0 OID 16488)
-- Dependencies: 1507
-- Data for Name: Page; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "Page" ("id_Url", "id_Url_Base", url, module) FROM stdin;
\.


--
-- TOC entry 1795 (class 0 OID 16501)
-- Dependencies: 1508
-- Data for Name: PostList; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "PostList" ("id_Url", "id_Word", "term_Frecuency") FROM stdin;
\.


--
-- TOC entry 1793 (class 0 OID 16472)
-- Dependencies: 1506
-- Data for Name: Word; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY "Word" (word, nr, "max_Tf", "id_Word") FROM stdin;
\.


--
-- TOC entry 1789 (class 2606 OID 16495)
-- Dependencies: 1507 1507
-- Name: pk_Id_Url; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "Page"
    ADD CONSTRAINT "pk_Id_Url" PRIMARY KEY ("id_Url");


--
-- TOC entry 1791 (class 2606 OID 16505)
-- Dependencies: 1508 1508 1508
-- Name: pk_PostList; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "PostList"
    ADD CONSTRAINT "pk_PostList" PRIMARY KEY ("id_Url", "id_Word");


--
-- TOC entry 1787 (class 2606 OID 16479)
-- Dependencies: 1506 1506
-- Name: pk_id_Word; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY "Word"
    ADD CONSTRAINT "pk_id_Word" PRIMARY KEY ("id_Word");


--
-- TOC entry 1792 (class 2606 OID 16496)
-- Dependencies: 1788 1507 1507
-- Name: fk_Id_Url_Base; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY "Page"
    ADD CONSTRAINT "fk_Id_Url_Base" FOREIGN KEY ("id_Url") REFERENCES "Page"("id_Url");


--
-- TOC entry 1800 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2012-02-03 19:04:10

--
-- PostgreSQL database dump complete
--

