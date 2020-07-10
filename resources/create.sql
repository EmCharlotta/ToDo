-- СОЗДАНИЕ ТАБЛИЦ
CREATE TABLE IF NOT EXISTS author(
--Создаем табличку с именем author, если такая табличка ещё не существует
--Перечисляем имена столбцов (author_id, name, age)
--Потом типы данных (serial, VARCHAR, INTEGER) - посмотреть документацию
--Потом доп. данные (PRIMARY KEY), 50 - максимум 50 символов, но не м.б.пустым
--номер начинается с 1 и с каждой строкой увеличивается на 1.
--даже если потом удалим один столбец, все равно последующие будут инкрементно расти на 1
   author_id serial PRIMARY KEY,
   name VARCHAR (50) NOT NULL,
    age INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS article(
	article_id serial PRIMARY KEY, --первичный ключ всегда уникальное значение,
	--это индексы (также как Uuid), хранятся в отсортированном виде, поиск по ним
	--проходит быстрее
	title VARCHAR (50) UNIQUE NOT NULL,
--UNIQUE- значение должны быть уникальными, не повторяться
	text TEXT NOT NULL,
	--Для типа VARCHAR есть ограничение по максимуму, поэтому если символов много, то тип TEXT
	created_on TIMESTAMP NOT NULL,
	--Дата и время (когда статья была написана)
	author_id INTEGER NOT NULL,
	--создаем связь под названием "author_article"
	--внешний ключ (author_id) в текущей табличке и ищет в табличке author по столбцу в ней
	--(author_id)
	CONSTRAINT author_article FOREIGN KEY (author_id) REFERENCES author(author_id)
	--не может удалить или изменить автора, если есть связанные статьи
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS nomination(
   nomination_id serial PRIMARY KEY,
   --Одна статья может быть в нескольких номинациях, при этом в одной номинации также может
   --быть несколько статей
   --Поэтому в такой ситуации необходимо создать промежуточную табличку
   --(сводную, все статьи-все номинации)
   name VARCHAR (150) NOT NULL
);

CREATE TYPE rate AS ENUM ('first', 'second', 'third');

CREATE TABLE IF NOT EXISTS nomination_article
(
  nomination_id integer NOT NULL,
  article_id integer NOT NULL,
  rating rate, -- rate from ENUM higher(first-second-third)
  PRIMARY KEY (nomination_id, article_id), --два первичных ключа из разных табличек
  CONSTRAINT nomination_fk FOREIGN KEY (nomination_id) --связь будет называться nomination_fk
      --из текущей таблички Foreign key - nomination_id поле
      REFERENCES nomination (nomination_id) MATCH FULL --в табличке nomination
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT article_fk FOREIGN KEY (article_id)
      REFERENCES article (article_id) MATCH FULL
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

-- ВСТАВКА ДАННЫХ В ТАБЛИЦУ
INSERT INTO author (name, age) --добавляем данные в таблицу "author" по столбцам
--name, age (во все not null столбцы)
VALUES
('qwerty', 28),
('reanno', 46),
('ivan334', 32),
('apple', 21),
('wind23', 23);

INSERT INTO article (title, text, created_on, author_id)
VALUES
('Stream API', 'Статья про Stream API', '2017-10-19 10:23', 1),
('Lambda', 'Статья про Lambda', '2019-03-27 16:33', 1),
('Java 13', 'Статья про Java 13', '2019-11-28 17:10', 2),
('Garbage Collectors', 'Статья про GC', '2019-08-07 11:55', 2),
('Hibernate', 'Статья про Hibernate', '2018-12-01 21:30', 2),
('Collections API', 'Collections API', '2019-11-22 22:29', 3);

INSERT INTO nomination (name)
VALUES
('Java 8'),
('Базы Данных'),
('Функциональное Программирование Java');

INSERT INTO nomination_article (nomination_id, article_id, rating)
VALUES
(1, 2, 'first'),
(1, 1, 'third'),
(2, 5, 'third'),
(3, 2, 'first'),
(3, 6, 'third');
