ALTER TABLE users
    ADD first_name VARCHAR(255);

ALTER TABLE users
    ADD last_name VARCHAR(255);

ALTER TABLE users
    ADD role VARCHAR(255);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);