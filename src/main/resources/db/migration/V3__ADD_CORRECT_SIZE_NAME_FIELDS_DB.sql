
ALTER TABLE masterboard.user
    MODIFY COLUMN first_name VARCHAR(255) NOT NULL,
    MODIFY COLUMN last_name VARCHAR(255) NOT NULL,
    MODIFY COLUMN address VARCHAR(255) NOT NULL,
    MODIFY COLUMN phone VARCHAR(255) NOT NULL,
    MODIFY COLUMN email VARCHAR(255) NOT NULL;

ALTER TABLE masterboard.project
    MODIFY COLUMN name VARCHAR(255) NOT NULL;

ALTER TABLE masterboard.case_type
    MODIFY COLUMN name VARCHAR(255) NOT NULL;

ALTER TABLE masterboard.case_type_flow
    MODIFY COLUMN stage VARCHAR(255) NOT NULL;

ALTER TABLE masterboard.card
    MODIFY COLUMN name VARCHAR(255) NOT NULL;

ALTER TABLE masterboard.card_item
    MODIFY COLUMN hours VARCHAR(5) NOT NULL;
