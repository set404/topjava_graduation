DELETE FROM USERS;
DELETE FROM USER_ROLES;
DELETE FROM RESTAURANT;
DELETE FROM DISH;
DELETE FROM VOTE;
ALTER SEQUENCE global_seq RESTART WITH 100000;


INSERT INTO USERS (email, name, password)
VALUES ('user@ya.ru', 'User', '{noop}password'),
       ('admin@ya.ru', 'Admin', '{noop}password');

INSERT INTO USER_ROLES (role, user_id)
VALUES ('USER', 100000),
       ('USER', 100001),
       ('ADMIN', 100001);

INSERT INTO RESTAURANT (name)
VALUES ('Ollis'),
       ('2berega'),
       ('larisuvannuhochu');

INSERT INTO DISH (date, name, price, restaurant_id)
VALUES (now(), 'Borsh', 200, 100002),
       (now(), 'Pizza', 700, 100002),
       (now(), 'Sushi', 500, 100003),
       (now(), 'Burger', 600, 100003),
       (now(), 'Shawerma', 150, 100004),
       (now(), 'Potatoes Free', 100, 100004);

INSERT INTO VOTE (date, user_id, restaurant_id)
VALUES (now(), 100000, 100002),
       (now(), 100001, 100003);