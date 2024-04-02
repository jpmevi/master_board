CREATE TABLE IF NOT EXISTS masterboard.user (
                                                id INT NOT NULL AUTO_INCREMENT,
                                                first_name VARCHAR(45) NOT NULL,
    last_name VARCHAR(45) NOT NULL,
    address VARCHAR(45) NOT NULL,
    phone VARCHAR(45) NOT NULL,
    email VARCHAR(45) NOT NULL,
    img_url VARCHAR(255) NOT NULL,
    role ENUM('administrator', 'project_manager', 'developer') NOT NULL,
    password VARCHAR(255) NOT NULL,
    salary_per_hour FLOAT,
    version INT,
    PRIMARY KEY (id),
    UNIQUE INDEX email_UNIQUE (email)
    );

CREATE TABLE IF NOT EXISTS masterboard.project (
                                                   id INT NOT NULL AUTO_INCREMENT,
                                                   user_id INT NOT NULL,
                                                   name VARCHAR(45) NOT NULL,
    description TEXT,
    background_url TEXT,
    is_active VARCHAR(45) NOT NULL,
    is_public TINYINT NOT NULL,
    disabled_reason TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    INDEX fk_BOARD_USER1_idx (user_id),
    CONSTRAINT fk_BOARD_USER1 FOREIGN KEY (user_id) REFERENCES masterboard.user (id)
    );

CREATE TABLE IF NOT EXISTS masterboard.project_user (
                                                        user_id INT NOT NULL,
                                                        project_id INT NOT NULL,
                                                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, project_id),
    INDEX fk_BOARD_USER_USER_idx (user_id),
    INDEX fk_BOARD_USER_PROJECT1_idx (project_id),
    CONSTRAINT fk_BOARD_USER_USER FOREIGN KEY (user_id) REFERENCES masterboard.user (id),
    CONSTRAINT fk_BOARD_USER_PROJECT1 FOREIGN KEY (project_id) REFERENCES masterboard.project (id)
    );

CREATE TABLE IF NOT EXISTS masterboard.case_type (
                                                     id INT NOT NULL AUTO_INCREMENT,
                                                     name VARCHAR(45) NOT NULL,
    description TEXT NOT NULL,
    project_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    label_color VARCHAR(7),
    PRIMARY KEY (id),
    INDEX fk_TYPE_CASE_PROJECT1_idx (project_id),
    CONSTRAINT fk_TYPE_CASE_PROJECT1 FOREIGN KEY (project_id) REFERENCES masterboard.project (id)
    );

CREATE TABLE IF NOT EXISTS masterboard.case_type_flow (
                                                          id INT NOT NULL AUTO_INCREMENT,
                                                          stage VARCHAR(45) NOT NULL,
    `order` INT NOT NULL,
    case_type_id INT NOT NULL,
    PRIMARY KEY (id),
    INDEX fk_CASE_TYPE_FLOW_CASE_TYPE1_idx (case_type_id),
    CONSTRAINT fk_CASE_TYPE_FLOW_CASE_TYPE1 FOREIGN KEY (case_type_id) REFERENCES masterboard.case_type (id)
    );

CREATE TABLE IF NOT EXISTS masterboard.card (
                                                id INT NOT NULL AUTO_INCREMENT,
                                                name VARCHAR(45) NOT NULL,
    description TEXT NOT NULL,
    due_date TIMESTAMP NOT NULL,
    reminder_date TIMESTAMP NOT NULL,
    is_active TINYINT NOT NULL,
    state ENUM('pending', 'canceled', 'completed'),
    canceled_reason TEXT,
    case_type_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    INDEX fk_CARD_CASE_TYPE1_idx (case_type_id),
    CONSTRAINT fk_CARD_CASE_TYPE1 FOREIGN KEY (case_type_id) REFERENCES masterboard.case_type (id)
    );

CREATE TABLE IF NOT EXISTS masterboard.card_item (
                                                     id INT NOT NULL AUTO_INCREMENT,
                                                     hours VARCHAR(45) NOT NULL,
    state ENUM('unassigned', 'assigned', 'in_progress', 'review', 'rejected', 'completed'),
    case_type_flow_id INT NOT NULL,
    card_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    INDEX fk_CARD_ITEM_CASE_TYPE_FLOW1_idx (case_type_flow_id),
    INDEX fk_CARD_ITEM_CARD1_idx (card_id),
    CONSTRAINT fk_CARD_ITEM_CASE_TYPE_FLOW1 FOREIGN KEY (case_type_flow_id) REFERENCES masterboard.case_type_flow (id),
    CONSTRAINT fk_CARD_ITEM_CARD1 FOREIGN KEY (card_id) REFERENCES masterboard.card (id)
    );

CREATE TABLE IF NOT EXISTS masterboard.comment (
                                                   id INT NOT NULL AUTO_INCREMENT,
                                                   comment TEXT NOT NULL,
                                                   user_id INT NOT NULL,
                                                   card_item_id INT NOT NULL,
                                                   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                                   updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                                   PRIMARY KEY (id),
    INDEX fk_COMMENT_USER1_idx (user_id),
    INDEX fk_COMMENT_CARD_ITEM1_idx (card_item_id),
    CONSTRAINT fk_COMMENT_USER1 FOREIGN KEY (user_id) REFERENCES masterboard.user (id),
    CONSTRAINT fk_COMMENT_CARD_ITEM1 FOREIGN KEY (card_item_id) REFERENCES masterboard.card_item (id)
    );

CREATE TABLE IF NOT EXISTS masterboard.card_user (
                                                     id INT NOT NULL AUTO_INCREMENT,
                                                     user_id INT NOT NULL,
                                                     card_id INT NOT NULL,
                                                     card_item_id INT,
                                                     INDEX fk_CARD_USER_USER1_idx (user_id),
    INDEX fk_CARD_USER_CARD1_idx (card_id),
    INDEX fk_CARD_USER_CARD_ITEM1_idx (card_item_id),
    PRIMARY KEY (id),
    CONSTRAINT fk_CARD_USER_USER1 FOREIGN KEY (user_id) REFERENCES masterboard.user (id),
    CONSTRAINT fk_CARD_USER_CARD1 FOREIGN KEY (card_id) REFERENCES masterboard.card (id),
    CONSTRAINT fk_CARD_USER_CARD_ITEM1 FOREIGN KEY (card_item_id) REFERENCES masterboard.card_item (id)
    );

CREATE TABLE IF NOT EXISTS masterboard.card_activity (
                                                         id INT NOT NULL AUTO_INCREMENT,
                                                         card_id INT NOT NULL,
                                                         user_id INT NOT NULL,
                                                         card_item_id INT,
                                                         activity TEXT NOT NULL,
                                                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                                         PRIMARY KEY (id),
    INDEX fk_CARD_ACTIVITY_CARD1_idx (card_id),
    INDEX fk_CARD_ACTIVITY_USER1_idx (user_id),
    INDEX fk_CARD_ACTIVITY_CARD_ITEM1_idx (card_item_id),
    CONSTRAINT fk_CARD_ACTIVITY_CARD1 FOREIGN KEY (card_id) REFERENCES masterboard.card (id),
    CONSTRAINT fk_CARD_ACTIVITY_USER1 FOREIGN KEY (user_id) REFERENCES masterboard.user (id),
    CONSTRAINT fk_CARD_ACTIVITY_CARD_ITEM1 FOREIGN KEY (card_item_id) REFERENCES masterboard.card_item (id)
    );
