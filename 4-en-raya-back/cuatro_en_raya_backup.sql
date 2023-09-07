--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2
-- Dumped by pg_dump version 15.2

-- Started on 2023-09-07 11:34:20

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 216 (class 1259 OID 16620)
-- Name: game; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.game (
    id integer NOT NULL,
    num_rows integer,
    num_columns integer,
    player_1 integer NOT NULL,
    player_2 integer
);


ALTER TABLE public.game OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16623)
-- Name: game_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.game ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.game_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 214 (class 1259 OID 16614)
-- Name: player; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.player (
    id integer NOT NULL,
    player_name text NOT NULL
);


ALTER TABLE public.player OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16619)
-- Name: jugador_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.player ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.jugador_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 218 (class 1259 OID 16624)
-- Name: moves; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.moves (
    id integer NOT NULL,
    date date,
    "row" integer,
    "column" integer,
    game_id integer NOT NULL,
    player_id integer NOT NULL
);


ALTER TABLE public.moves OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16627)
-- Name: moves_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.moves ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.moves_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 3327 (class 0 OID 16620)
-- Dependencies: 216
-- Data for Name: game; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.game (id, num_rows, num_columns, player_1, player_2) FROM stdin;
\.


--
-- TOC entry 3329 (class 0 OID 16624)
-- Dependencies: 218
-- Data for Name: moves; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.moves (id, date, "row", "column", game_id, player_id) FROM stdin;
\.


--
-- TOC entry 3325 (class 0 OID 16614)
-- Dependencies: 214
-- Data for Name: player; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.player (id, player_name) FROM stdin;
1	Tiburcio
2	Javi
\.


--
-- TOC entry 3336 (class 0 OID 0)
-- Dependencies: 217
-- Name: game_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.game_id_seq', 1, false);


--
-- TOC entry 3337 (class 0 OID 0)
-- Dependencies: 215
-- Name: jugador_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.jugador_id_seq', 2, true);


--
-- TOC entry 3338 (class 0 OID 0)
-- Dependencies: 219
-- Name: moves_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.moves_id_seq', 1, false);


-- Completed on 2023-09-07 11:34:20

--
-- PostgreSQL database dump complete
--

