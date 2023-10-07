--liquibase formatted sq
--changeset denis:1

CREATE TABLE IF NOT EXISTS customer (  -- Таблица: Пользователь
 id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,          -- уникальный id
chat_id BIGINT NOT NULL,    -- id Telegram чата
surname VARCHAR(25) , -- фамилия
name VARCHAR(25),    -- имя
second_name VARCHAR(25), -- отчество
phone VARCHAR(12),       -- тлф формата +70000000000
address TEXT,             -- адрес
email VARCHAR(30)        -- почта клиента
);

CREATE TABLE IF NOT EXISTS pets (    -- Таблица: Приют
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    age INTEGER NOT NULL,                         -- возраст питомца
    name          VARCHAR(20),                    -- имя питомца
    type_pets     VARCHAR(20),                    -- тип животного
    breed         VARCHAR(20),                    -- порода
    id_customer   BIGINT references customer,
    id_shelter    BIGINT references shelters
);

CREATE TABLE IF NOT EXISTS shelters ( -- Таблица: приют
    id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name    VARCHAR(20),                    -- имя приюта (совпадает с type_pets из таблицы pets)
    adress  VARCHAR(255),                   -- адрес прита
    location POINT NOT NULL          -- координаты приюта
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
INSERT INTO volunteer (id, sex, chat_id, name, phone, second_name, surname,  id_shelter) VALUES (1, 'муж', 1284536796, 'Иван', 89304148202, 'Иванов',  'Иванович', 1);