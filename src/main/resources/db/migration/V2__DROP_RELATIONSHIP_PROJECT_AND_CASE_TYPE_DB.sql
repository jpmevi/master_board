
ALTER TABLE masterboard.case_type
    DROP FOREIGN KEY fk_TYPE_CASE_PROJECT1;

ALTER TABLE masterboard.card
    ADD COLUMN project_id INT NOT NULL,
    ADD CONSTRAINT fk_CARD_PROJECT
        FOREIGN KEY (project_id) REFERENCES masterboard.project (id);
