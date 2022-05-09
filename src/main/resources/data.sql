INSERT INTO USERS (email, name, password)
VALUES ('user@ya.ru', 'User', '{noop}password'),
       ('admin@ya.ru', 'Admin', '{noop}password');

INSERT INTO USER_ROLES (role, user_id)
VALUES ('USER', 1),
       ('USER', 2),
       ('ADMIN', 2);

INSERT INTO RESTAURANT (name)
VALUES ('Ollis'),
       ('2berega'),
       ('larisuvannuhochu');

INSERT INTO DISH (date, name, price, restaurant_id)
VALUES (now(), 'Borsh', 200, 1),
       (now(), 'Pizza', 700, 1),
       (now(), 'Sushi', 500, 2),
       (now(), 'Burger', 600, 2),
       (now(), 'Shawerma', 150, 3),
       (now(), 'Potatoes Free', 100, 3);

INSERT INTO VOTE (date, user_id, restaurant_id)
VALUES (now(), 1, 1),
       (now(), 2, 3);