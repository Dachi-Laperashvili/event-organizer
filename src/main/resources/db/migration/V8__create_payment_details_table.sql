CREATE TABLE task_payment_details
(
    task_id      BIGINT NOT NULL,
    contribution DECIMAL,
    user_id      BIGINT NOT NULL,
    CONSTRAINT pk_task_payment_details PRIMARY KEY (task_id, user_id)
);

ALTER TABLE task_payment_details
    ADD CONSTRAINT fk_task_payment_details_on_task FOREIGN KEY (task_id) REFERENCES tasks (id);

ALTER TABLE task_payment_details
    ADD CONSTRAINT fk_task_payment_details_on_user FOREIGN KEY (user_id) REFERENCES users (id);