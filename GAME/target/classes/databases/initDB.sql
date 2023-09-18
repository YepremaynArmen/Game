-- DROP TABLE IF EXISTS public."USER_PERSONALS";
CREATE TABLE IF NOT EXISTS public.user_personals
(
    user_id uuid NOT NULL DEFAULT gen_random_uuid(),
    first_name text COLLATE pg_catalog."default" NOT NULL DEFAULT 'invite'::text,
    last_name text COLLATE pg_catalog."default" NOT NULL DEFAULT 'invite'::text,
    patronymic_name text COLLATE pg_catalog."default",
    birth_day date,
    sex integer,
    create_date date DEFAULT now(),
    register_date date,
    phone text COLLATE pg_catalog."default",
    mail text COLLATE pg_catalog."default",
    status integer,
    change_date date,
    CONSTRAINT user_personals_pkey PRIMARY KEY (user_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."USER_PERSONALS"
    OWNER to postgres;