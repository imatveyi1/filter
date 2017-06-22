# filter
[![Build Status](https://travis-ci.org/imatveyi1/filter.svg?branch=master)](https://travis-ci.org/imatveyi1/filter)

сервис вызывается по ссылке http://localhost:4567/hello?nameFilter=
при запуске установить параметры коннекта к базе данных postgres
-Durl=jdbc:postgresql://localhost:5432/store
-Dlogin=login - логин базы данных 
-Dpassword=password - пароль

создать базу данных store и создать таблицу 
CREATE TABLE public.contacts
(
    id integer NOT NULL,
    name character varying(64) COLLATE pg_catalog."default",
    CONSTRAINT id PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.contacts
    OWNER to postgres;

-- Index: index_id

-- DROP INDEX public.index_id;

CREATE UNIQUE INDEX index_id
    ON public.contacts USING btree
    (id)
    TABLESPACE pg_default;
