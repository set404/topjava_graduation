DROP TABLE IF EXISTS USERS CASCADE;
DROP TABLE IF EXISTS USER_ROLES CASCADE;
DROP TABLE IF EXISTS RESTAURANT CASCADE;
DROP TABLE IF EXISTS DISH CASCADE;
DROP TABLE IF EXISTS VOTE CASCADE;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE USERS
(
    id         INTEGER DEFAULT nextval('global_seq') PRIMARY KEY,
    name       VARCHAR(255)            NOT NULL,
    email      VARCHAR(255)            NOT NULL,
    password   VARCHAR(255)            NOT NULL,
    enabled    BOOL      DEFAULT TRUE  NOT NULL,
    CONSTRAINT users_email_idx UNIQUE (email)
);

CREATE TABLE USER_ROLES
(
    user_id INTEGER NOT NULL,
    role    VARCHAR(255),
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE
);

CREATE TABLE RESTAURANT
(
    id   INTEGER DEFAULT nextval('global_seq') PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE DISH
(
    id            INTEGER DEFAULT nextval('global_seq') PRIMARY KEY,
    date          DATE DEFAULT now() NOT NULL,
    name          VARCHAR(255) NOT NULL,
    price         DOUBLE       NOT NULL,
    restaurant_id INTEGER      NOT NULL,
    CONSTRAINT dish_restaurant_idx UNIQUE (id, restaurant_id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);


CREATE TABLE VOTE
(
    id            INTEGER DEFAULT nextval('global_seq') PRIMARY KEY,
    date          DATE DEFAULT now() NOT NULL,
    user_id       INTEGER                   NOT NULL,
    restaurant_id INTEGER                   NOT NULL,
    CONSTRAINT vote_date_user_idx UNIQUE (date, user_id),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);