
-- Eliminar la restricción de clave externa
ALTER TABLE masterboard.case_type
    DROP FOREIGN KEY fk_TYPE_CASE_PROJECT1;

-- Eliminar el índice
DROP INDEX fk_TYPE_CASE_PROJECT1_idx ON masterboard.case_type;

-- Eliminar la columna
ALTER TABLE masterboard.case_type
    DROP COLUMN project_id;

ALTER TABLE masterboard.card
    ADD COLUMN project_id INT NOT NULL,
    ADD CONSTRAINT fk_CARD_PROJECT
        FOREIGN KEY (project_id) REFERENCES masterboard.project (id);
