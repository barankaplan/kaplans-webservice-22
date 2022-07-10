drop table if exists movies cascade;
create table movies
(
    movie_id   bigserial          not null
        constraint movies_pkey
            primary key,
    name       varchar(256)       not null,
    scene_time date               not null,
    rating     bigint default 0   not null,
    cost       bigint             not null,
    imdb       real   default 0.0 not null
);

drop table if exists directors cascade;
create table directors
(
    director_id bigserial    not null
        constraint directors_pkey
            primary key,
    name        varchar(256) not null,
    birt_date   date         not null
);



insert into directors(name, birt_date)
VALUES ('kaplan', '1989-10-05');
insert into directors(name, birt_date)
VALUES ('nolan', '1970-07-30');
insert into directors(name, birt_date)
VALUES ('p jackson', '1961-10-31');

insert into movies(name, scene_time, rating, cost, imdb)
VALUES ('kaplan''s movie', '2020-01-01', 400, 900, 4.5);
insert into movies(name, scene_time, rating, cost, imdb)
VALUES ('lotr', '1960-09-13', 1400, 300, 8.7);
insert into movies(name, scene_time, rating, cost, imdb)
VALUES ('star wars', '1989-01-01', 1400, 300, 8.7);
--
--
--
drop table if exists movies_to_director cascade;
create table movies_to_director
(
    movies_to_director_id bigserial primary key,
    movie_id              bigint references movies (movie_id),
    director_id           bigint references directors (director_id),
    movie_count           int not null

);

insert into movies_to_director(movie_id, director_id, movie_count)
VALUES (1, 1, 4);
insert into movies_to_director(movie_id, director_id, movie_count)
VALUES (2, 2, 4);
insert into movies_to_director(movie_id, director_id, movie_count)
VALUES (3, 3, 4);



--    part 2 is below!
-- --


-- create or replace function get_movie_detail_by_name_2(varchar(100))
--     returns varchar(100) as
--
-- $$
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




insert into book (book_name, genre)
values ('Life of Pi', 'Adventure');
insert into book (book_name, genre)
values ('The Call of the Wild', 'Adventure');
insert into book (book_name, genre)
values ('To Kill a Mockingbird', 'Classics');
insert into book (book_name, genre)
values ('The Night Fire', 'Mystery');
insert into book (book_name, genre)
values ('Watchmen', 'Novel');
--
--
--
--
--
--
--also have to used for step 4
insert into roles (role_id, name) VALUES (3,'ROLE_ADMIN');
insert into roles (role_id, name) VALUES (2,'ROLE_USER');
-- this is where we use PasswordEncoderGenerator
insert into users (user_id, e_mail, password, user_name) VALUES (3,'admin@gmail.com','$2a$10$405vU36hMHhBHt/o.iD2Iu2fPGoXA7osXNjld3N2Ncp6maKDPnpSq','admin');
insert into users (user_id, e_mail, password, user_name) VALUES (2,'kaplan@gmail.com','$2a$10$F4WrJ2eWAZ2cIFM0jHi5YePvveYXy1QlGoP0h7uoIdhL/z/UY7Kmu','kaplan');

insert into users_roles(user_id, role_id) VALUES (3,3);
insert into users_roles(user_id, role_id) VALUES (2,2)