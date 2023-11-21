DROP TABLE IF EXISTS users_servlet;

CREATE TABLE users_servlet(
    id          BIGSERIAL,
    first_name  VARCHAR(30),
    last_name   VARCHAR(40),
    date_birth  DATE,
    country     VARCHAR(30),
	gender		VARCHAR(10),
    CONSTRAINT user_PK PRIMARY KEY (id)
);