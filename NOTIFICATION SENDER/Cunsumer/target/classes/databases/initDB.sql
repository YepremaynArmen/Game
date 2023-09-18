CREATE TABLE IF NOT EXISTS public.letters
(
    id integer NOT NULL DEFAULT nextval('letters_id_seq'::regclass),
    transaction_id uuid NOT NULL,
    body text COLLATE pg_catalog."default",
    address_to jsonb[] NOT NULL,
    sent_date date NOT NULL,
    status integer,
    response_msg text COLLATE pg_catalog."default",
    CONSTRAINT letters_pkey PRIMARY KEY (id)
)