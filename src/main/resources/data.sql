INSERT INTO USERS (email, name, password, registered)
VALUES ('user@ya.ru', 'User', '{noop}password', DATEADD('DAY', -10, CURDATE())),
       ('admin@ya.ru', 'Admin', '{noop}password', DATEADD('DAY', -15, CURDATE()));

INSERT INTO USER_ROLES (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO RESTAURANT (name)
VALUES ('Ollis'),
       ('2berega'),
       ('larisuvannuhochu');


INSERT INTO DISH (name, price, restaurant_id)
VALUES ('Borsh', 200, 1),
       ('Pizza', 700, 1),
       ('Sushi', 500, 2),
       ('Burger', 600, 2),
       ('Shawerma', 150, 3),
       ('Potatoes Free', 100, 3);

INSERT INTO MENU (name, date, restaurant_id)
VALUES ('Ollis 1', CURRENT_DATE, 1),
       ('Ollis 2', CURRENT_DATE, 1),
       ('2berega 1', CURRENT_DATE, 2),
       ('2berega 2', CURRENT_DATE, 2),
       ('larisuvannuhochu', CURRENT_DATE, 3),
       ('larisuvannuhochu', CURRENT_DATE, 3);

INSERT INTO VOTE (date, user_id, restaurant_id)
VALUES (CURDATE(), 1, 2),
       (DATEADD('DAY', -1, CURDATE()), 1, 3),
       (DATEADD('DAY', -2, CURDATE()), 1, 1),
       (CURDATE(), 2, 3),
       (DATEADD('DAY', -1, CURDATE()), 2, 1),
       (DATEADD('DAY', -2, CURDATE()), 2, 2);