CREATE TABLE IF NOT EXISTS events_users
(
    events_id BIGINT NOT NULL,
    users_id  BIGINT NOT NULL,
    CONSTRAINT pk_events_users PRIMARY KEY (events_id, users_id)
);