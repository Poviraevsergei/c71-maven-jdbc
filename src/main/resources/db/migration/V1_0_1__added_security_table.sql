create table public.users
(
    id            bigint  default nextval('users_id_seq'::regclass) not null
        constraint users_pk
            primary key,
    username      varchar(100)                                      not null,
    user_password varchar(100)                                      not null,
    created       timestamp(6)                                      not null,
    changed       timestamp(6),
    age           integer default 18
);

alter table public.users
    owner to postgres;

create trigger trigger_users
    after insert or update or delete
    on public.users
    for each row
execute procedure public.add_to_log();

create table public.telephone
(
    id               bigserial
        constraint telephone_pk
            primary key,
    operator         varchar(10),
    telephone_number varchar not null,
    user_id          integer
        constraint user_id___fk
            references public.users
            on update cascade on delete cascade
);

alter table public.telephone
    owner to postgres;

create unique index telephone_num__index
    on public.telephone (telephone_number);

create table public.product
(
    id           bigserial
        constraint product_pk
            primary key,
    product_name varchar,
    cost         integer
);

alter table public.product
    owner to postgres;

create table public.l_users_product
(
    id         bigserial
        constraint l_users_product_pk
            primary key,
    user_id    bigint                              not null
        constraint l_users_product_users_id_fk
            references public.users
            on update cascade on delete cascade,
    product_id bigint                              not null
        constraint l_users_product_product_id_fk
            references public.product
            on update cascade on delete cascade,
    created    timestamp default CURRENT_TIMESTAMP not null
);

alter table public.l_users_product
    owner to postgres;

create table public.logs
(
    id    bigserial
        primary key,
    text  text,
    added timestamp
);

alter table public.logs
    owner to postgres;

create table public.author
(
    id          bigserial
        constraint author_pk
            primary key,
    author_name varchar(10)
);

alter table public.author
    owner to postgres;

create table public.books
(
    id        bigserial
        constraint books_pk
            primary key,
    book_name varchar(10)
);

alter table public.books
    owner to postgres;

create table public.l_author_book
(
    id        bigserial
        constraint l_author_book_pk
            primary key,
    author_id integer not null
        constraint l_author_book_author_id_fk
            references public.author
            on update cascade on delete cascade,
    book_id   integer not null
        constraint l_author_book_books_id_fk
            references public.books
            on update cascade on delete cascade
);

alter table public.l_author_book
    owner to postgres;

create table public.pages
(
    id      bigserial
        constraint pages_pk
            primary key,
    book_id integer not null
        constraint page_books_id_fk
            references public.books
            on update cascade on delete cascade,
    text    varchar(50)
);

alter table public.pages
    owner to postgres;

create table public.flyway_schema_history
(
    installed_rank integer                 not null
        constraint flyway_schema_history_pk
            primary key,
    version        varchar(50),
    description    varchar(200)            not null,
    type           varchar(20)             not null,
    script         varchar(1000)           not null,
    checksum       integer,
    installed_by   varchar(100)            not null,
    installed_on   timestamp default now() not null,
    execution_time integer                 not null,
    success        boolean                 not null
);

alter table public.flyway_schema_history
    owner to postgres;

create index flyway_schema_history_s_idx
    on public.flyway_schema_history (success);

create table public.security
(
    id       bigserial
        constraint security_pk
            primary key,
    login    varchar(10) not null,
    password varchar(10) not null,
    role     varchar(10) default 'USER'::character varying,
    user_id  bigint      not null
        constraint user_id___fk
            references public.users
            on update cascade on delete cascade
);

alter table public.security
    owner to postgres;

