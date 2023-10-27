--liquibase formatted sq
--changeset marat:1

create table if not exists customer (  -- Таблица: Пользователь
 id bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,          -- уникальный id
chat_id BIGINT NOT NULL,    -- id Telegram чата
surname VARCHAR(25) , -- фамилия
name VARCHAR(25),    -- имя
second_name VARCHAR(25), -- отчество
phone VARCHAR(12),       -- тлф формата +70000000000
address TEXT,             -- адрес
email VARCHAR(30)        -- почта клиента
);

create table if not exists shelters ( -- Таблица: приют
    id bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name    VARCHAR(20),                    -- имя приюта (совпадает с type_pets из таблицы pets)
    adress  VARCHAR(255),                   -- адрес приюта
   location VARCHAR(100) NOT NULL          -- координаты приюта

);
create table if not exists pets (    -- Таблица: Приют
    id bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    age INTEGER NOT NULL,                         -- возраст питомца
    name          VARCHAR(20),                    -- имя питомца
    type_pets     VARCHAR(20),                    -- тип животного
    breed         VARCHAR(20),                    -- порода
    id_customer   BIGINT references customer,
    id_shelter    BIGINT references shelters
);



INSERT INTO public.pets
(age, name)
SELECT 5, 'кот Васька'
    WHERE NOT EXISTS(
        SELECT id, name FROM public.pets WHERE name = 'кот Васька'
    );

INSERT INTO public.pets
(age, name)
SELECT 1, 'котенок Мурзик'
    WHERE NOT EXISTS(
        SELECT id, name FROM public.pets WHERE name = 'котенок Мурзик'
    );

INSERT INTO public.pets
(age, name)
SELECT 4, 'пес Барбос'
    WHERE NOT EXISTS(
        SELECT id, name FROM public.pets WHERE name = 'пес Барбос'
    );

create table if not exists volunteer    -- Таблица: волонтеров
(
    id          bigint generated by default as identity
    primary key,
    sex         varchar(20),            -- пол волонтера
    chat_id     bigint not null,        -- id чата волонтера
    name        varchar(30),
    phone       varchar(20),
    second_name varchar(30),
    surname     varchar(30)
    );
--changeset denis:2
ALTER TABLE shelters ALTER COLUMN location type VARCHAR(255);
--changeset denis:3
ALTER TABLE volunteer ADD COLUMN if not exists id_shelter BIGINT references shelters;
--changeset denis:4
update DATABASECHANGELOG set md5sum = null where true;
--changeset denis:5
create table if not exists report    -- Таблица: отчета
(
    id          bigint generated by default as identity
        primary key,
    petReport    varchar(255),         -- краткий отчет
    date        DATE,                  -- дата сдачи отчета
    id_pets   BIGINT references pets
);
--changeset denis:6
create table if not exists photoPet    -- Таблица: фотоотчета
(

    id          bigint generated by default as identity
        primary key,
    filePath     varchar(255),
    fileSize     BIGINT,
    mediaType    varchar(255),
    id_pets   BIGINT references pets
);

--changeset denis:8
update DATABASECHANGELOG set md5sum = null where true;

create table if not exists report
(
    id         bigint generated by default as identity primary key,
    date       timestamp,
    file_path  varchar(255),
    file_size  bigint,
    media_type varchar(255),
    pet_report varchar(255),
    id_pets     bigint
    constraint fk1kk5qc2sk2cc71fibvnto90ax
    references pets
    );
--changeset denis:9
ALTER TABLE photo_pet ADD COLUMN if not exists id_report BIGINT references report;
--changeset denis:10
ALTER TABLE customer ADD COLUMN if not exists id_pets BIGINT references pets;
--changeset denis:11
ALTER TABLE pets ALTER COLUMN decision_date TYPE Date;
--changeset denis:12
ALTER TABLE pets ADD COLUMN if not exists probation_status varchar(30);