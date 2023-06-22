CREATE TABLE IF NOT EXISTS tasks
(
    id       BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name     VARCHAR(255),
    event_id BIGINT,
    user_id  BIGINT,
    state    VARCHAR(255),
    CONSTRAINT pk_tasks PRIMARY KEY (id)
);
