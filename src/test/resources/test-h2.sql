
-- insert into BOOK (BOOKNAME, GENRE)
-- values ('Life of Pi', 'Adventure');
-- insert into BOOK (BOOKNAME, GENRE)
-- values ('The Call of the Wild', 'Adventure');
-- insert into BOOK (BOOKNAME, GENRE)
-- values ('To Kill a Mockingbird', 'Classics');
-- insert into BOOK (BOOKNAME, GENRE)
-- values ('The Night Fire', 'Mystery');
-- insert into BOOK (BOOKNAME, GENRE)
-- values ('Watchmen', 'Novel');

-- drop table if exists movies cascade ;
-- create table movies
-- (
--     movie_id   bigserial          not null
--         constraint movies_pkey
--             primary key,
--     name       varchar(256)       not null,
--     scene_time date               not null,
--     rating     bigint default 0   not null,
--     cost       bigint              not null,
--     imdb       real   default 0.0 not null
-- );
--
-- drop table if exists directors cascade ;
-- create table directors
-- (
--     director_id   bigserial          not null
--         constraint directors_pkey
--             primary key,
--     name       varchar(256)       not null,
--     birt_date date               not null
-- );
--
--
--
--
-- insert into BOOK (BOOK_NAME, GENRE)
-- values ('Life of Pi', 'Adventure');
-- insert into BOOK (BOOK_NAME, GENRE)
-- values ('The Call of the Wild', 'Adventure');
-- insert into BOOK (BOOK_NAME, GENRE)
-- values ('To Kill a Mockingbird', 'Classics');
-- insert into BOOK (BOOK_NAME, GENRE)
-- values ('The Night Fire', 'Mystery');
-- insert into BOOK (BOOK_NAME, GENRE)
-- values ('Watchmen', 'Novel');
--
-- insert into directors(name, birt_date) VALUES ('kaplan','1989-10-05');
-- insert into directors(name, birt_date) VALUES ('nolan','1970-07-30');
-- insert into directors(name, birt_date) VALUES ('p jackson','1961-10-31');
--
-- insert into movies(name, scene_time, rating, cost, imdb) VALUES ('kaplan''s movie','2020-01-01',400,900,4.5);
-- insert into movies(name, scene_time, rating, cost, imdb) VALUES ('lotr','1960-09-13',1400,300,8.7);
--
--
-- drop table if exists movies_to_director cascade ;
-- create table movies_to_director
-- (
--     movies_to_director_id   bigserial primary key,
--     movie_id bigint references  movies(movie_id),
--     director_id bigint references  directors(director_id),
--     movie_count int not null
--
-- );
--
-- insert into movies_to_director(movie_id, director_id, movie_count) VALUES (1,1,4);
-- insert into movies_to_director(movie_id, director_id, movie_count) VALUES (2,3,4);
-- insert into movies_to_director(movie_id, director_id, movie_count) VALUES (3,2,4);
--
--
--
-- create or replace function get_movie_detail_by_name(varchar(50))
--     returns varchar(1000) as $$
-- declare
--     name varchar(100);
-- begin
--     select (m.name, m.scene_time, m.rating, m.cost, d.name)
--     from directors d
--              inner join movies_to_director md on md.director_id = d.director_id
--              inner join
--          movies m on md.movie_id = m.movie_id
--     where m.name = $1
--     into name;
--
--     return name;
-- end;
-- $$ language plpgsql;



--
insert into BOOK (BOOKNAME, genre)
values ('Life of Pi', 'Adventure');
insert into BOOK (BOOKNAME, genre)
values ('The Call of the Wild', 'Adventure');
insert into BOOK (BOOKNAME, genre)
values ('To Kill a Mockingbird', 'Classics');
insert into BOOK (BOOKNAME, genre)
values ('The Night Fire', 'Mystery');
insert into BOOK (BOOKNAME, genre)
values ('Watchmen', 'Novel');

