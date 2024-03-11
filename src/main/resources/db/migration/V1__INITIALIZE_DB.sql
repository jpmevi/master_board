-- -----------------------------------------------------
-- Table user
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS masterboard.user (
                                                id INT NOT NULL AUTO_INCREMENT,
                                                first_name VARCHAR(45) NOT NULL,
    last_name VARCHAR(45) NOT NULL,
    address VARCHAR(45) NOT NULL,
    phone VARCHAR(45) NOT NULL,
    email VARCHAR(45) NOT NULL,
    img_url VARCHAR(255) NOT NULL,
    role ENUM('administrator', 'worker') NOT NULL,
    password VARCHAR(255) NOT NULL,
    salary_per_hour FLOAT,
    version INT,
    PRIMARY KEY (id),
    UNIQUE INDEX email_UNIQUE (email)
    );

-- -----------------------------------------------------
-- Table board
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS masterboard.board (
                                                 id INT NOT NULL AUTO_INCREMENT,
                                                 user_id INT NOT NULL,
                                                 name VARCHAR(45) NOT NULL,
    background_url VARCHAR(255) NOT NULL,
    id_public TINYINT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    PRIMARY KEY (id),
    INDEX fk_board_user1_idx (user_id),
    CONSTRAINT fk_board_user1
    FOREIGN KEY (user_id)
    REFERENCES masterboard.user (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    );

-- -----------------------------------------------------
-- Table board_user
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS masterboard.board_user (
                                                      user_id INT NOT NULL,
                                                      board_id INT NOT NULL,
                                                      PRIMARY KEY (user_id, board_id),
    INDEX fk_board_user_user_idx (user_id),
    INDEX fk_board_user_board1_idx (board_id),
    CONSTRAINT fk_board_user_user
    FOREIGN KEY (user_id)
    REFERENCES masterboard.user (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT fk_board_user_board1
    FOREIGN KEY (board_id)
    REFERENCES masterboard.board (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    );

-- -----------------------------------------------------
-- Table list
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS masterboard.list (
                                                id INT NOT NULL AUTO_INCREMENT,
                                                board_id INT NOT NULL,
                                                name VARCHAR(45) NOT NULL,
    position INT NOT NULL,
    PRIMARY KEY (id),
    INDEX fk_list_board1_idx (board_id),
    CONSTRAINT fk_list_board1
    FOREIGN KEY (board_id)
    REFERENCES masterboard.board (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    );

-- -----------------------------------------------------
-- Table card
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS masterboard.card (
                                                id INT NOT NULL AUTO_INCREMENT,
                                                list_id INT NOT NULL,
                                                name VARCHAR(45) NOT NULL,
    description TEXT NOT NULL,
    due_date TIMESTAMP NOT NULL,
    reminder_date TIMESTAMP NOT NULL,
    is_active TINYINT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    hours INT NOT NULL,
    PRIMARY KEY (id),
    INDEX fk_card_list1_idx (list_id),
    CONSTRAINT fk_card_list1
    FOREIGN KEY (list_id)
    REFERENCES masterboard.list (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    );

-- -----------------------------------------------------
-- Table comment
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS masterboard.comment (
                                                   id INT NOT NULL AUTO_INCREMENT,
                                                   user_id INT NOT NULL,
                                                   card_id INT NOT NULL,
                                                   comment TEXT NOT NULL,
                                                   created_at TIMESTAMP NOT NULL,
                                                   PRIMARY KEY (id),
    INDEX fk_comment_user1_idx (user_id),
    INDEX fk_comment_card1_idx (card_id),
    CONSTRAINT fk_comment_user1
    FOREIGN KEY (user_id)
    REFERENCES masterboard.user (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT fk_comment_card1
    FOREIGN KEY (card_id)
    REFERENCES masterboard.card (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    );

-- -----------------------------------------------------
-- Table card_user
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS masterboard.card_user (
                                                     user_id INT NOT NULL,
                                                     card_id INT NOT NULL,
                                                     PRIMARY KEY (user_id, card_id),
    INDEX fk_card_user_card1_idx (card_id),
    CONSTRAINT fk_card_user_user1
    FOREIGN KEY (user_id)
    REFERENCES masterboard.user (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT fk_card_user_card1
    FOREIGN KEY (card_id)
    REFERENCES masterboard.card (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    );

-- -----------------------------------------------------
-- Table checklist
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS masterboard.checklist (
                                                     id INT NOT NULL AUTO_INCREMENT,
                                                     card_id INT NOT NULL,
                                                     name VARCHAR(45) NOT NULL,
    PRIMARY KEY (id),
    INDEX fk_checklist_card1_idx (card_id),
    CONSTRAINT fk_checklist_card1
    FOREIGN KEY (card_id)
    REFERENCES masterboard.card (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    );

-- -----------------------------------------------------
-- Table checklist_item
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS masterboard.checklist_item (
                                                          id INT NOT NULL AUTO_INCREMENT,
                                                          checklist_id INT NOT NULL,
                                                          name VARCHAR(45) NOT NULL,
    is_checked TINYINT NOT NULL,
    position INT NOT NULL,
    PRIMARY KEY (id),
    INDEX fk_checklist_item_checklist1_idx (checklist_id),
    CONSTRAINT fk_checklist_item_checklist1
    FOREIGN KEY (checklist_id)
    REFERENCES masterboard.checklist (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    );

-- -----------------------------------------------------
-- Table card_activity
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS masterboard.card_activity (
                                                         id INT NOT NULL AUTO_INCREMENT,
                                                         card_id INT NOT NULL,
                                                         user_id INT NOT NULL,
                                                         activity TEXT NOT NULL,
                                                         created_at TIMESTAMP NOT NULL,
                                                         PRIMARY KEY (id),
    INDEX fk_card_activity_card1_idx (card_id),
    INDEX fk_card_activity_user1_idx (user_id),
    CONSTRAINT fk_card_activity_card1
    FOREIGN KEY (card_id)
    REFERENCES masterboard.card (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT fk_card_activity_user1
    FOREIGN KEY (user_id)
    REFERENCES masterboard.user (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    );
