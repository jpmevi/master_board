
-- DATA FOR TABLE 'CARD'
INSERT INTO card (name, description, due_date, reminder_date, is_active, state, canceled_reason, case_type_id, project_id)
SELECT
    CONCAT('Card ', ct.name) AS name,
    CONCAT('Description of Card ', ct.name) AS description,
    CURRENT_TIMESTAMP AS due_date,
    CURRENT_TIMESTAMP AS reminder_date,
    FLOOR(RAND() * 2) AS is_active, -- Estado aleatorio
    CASE FLOOR(RAND() * 3)
        WHEN 0 THEN 'pending'
        WHEN 1 THEN 'canceled'
        ELSE 'completed'
        END AS state, -- Estado aleatorio
    CASE FLOOR(RAND() * 3)
        WHEN 0 THEN 'Reason 1'
        WHEN 1 THEN 'Reason 2'
        ELSE 'Reason 3'
        END AS canceled_reason, -- Razón de cancelación aleatoria
    ct.id AS case_type_id,
    p.id AS project_id
FROM
    case_type ct
        CROSS JOIN
    project p;

-- DATA FOR TABLE CARD_ITEM asociados a cada card y case type flow
INSERT INTO card_item (hours, state, case_type_flow_id, card_id)
    SELECT
        CONCAT(FLOOR(RAND() * 8), ':', FLOOR(RAND() * 60)) AS hours,
        CASE FLOOR(RAND() * 6)
            WHEN 0 THEN 'unassigned'
            WHEN 1 THEN 'assigned'
            WHEN 2 THEN 'in_progress'
            WHEN 3 THEN 'review'
            WHEN 4 THEN 'rejected'
            ELSE 'completed'
            END AS state, -- Estado aleatorio
        ctf.id AS case_type_flow_id,
        c.id AS card_id
    FROM
        case_type_flow ctf
            JOIN
        card c ON c.case_type_id = ctf.case_type_id;


-- DATA FOR CARD USER
INSERT INTO card_user (user_id, card_id, card_item_id)
    SELECT
        user_id,
        card_id,
        card_item_id
    FROM
        (
            SELECT
                u.id AS user_id,
                c.id AS card_id,
                ci.id AS card_item_id,
                ROW_NUMBER() OVER (PARTITION BY c.id ORDER BY RAND()) AS rn -- Generar un número de fila aleatorio para cada card
            FROM
                user u
                    JOIN
                card c
                    JOIN
                card_item ci ON ci.card_id = c.id
            WHERE
                u.role = 3 -- Seleccionar solo developers (role = 3)
              AND ci.state <> 'unassigned' -- Seleccionar solo card_items con estado diferente de 'unassigned'
        ) AS sub
    WHERE
        rn <= 3; -- Asignar solo 3 developers por cada card

-- DATA FOR CARD ACTIVITY
INSERT INTO card_activity (card_id, user_id, card_item_id, activity)
    SELECT
        cu.card_id,
        cu.user_id,
        cu.card_item_id,
        CONCAT('State changed to ', ci.state) AS activity
    FROM
        (
            SELECT DISTINCT
                card_id,
                user_id,
                card_item_id
            FROM
                card_user
        ) AS cu
            JOIN
        card_item ci ON cu.card_item_id = ci.id;
