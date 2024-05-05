-- CREATE DATABASE CourseworkDB;

CREATE SCHEMA IF NOT EXISTS unmatched;

DROP TABLE IF EXISTS unmatched.Set CASCADE;
CREATE TABLE unmatched.Set
(
    id               SERIAL PRIMARY KEY,
    name             TEXT UNIQUE NOT NULL,
    number_of_heroes int2        NOT NULL
);

DROP TABLE IF EXISTS unmatched.Map CASCADE;
CREATE TABLE unmatched.Map
(
    id         SERIAL PRIMARY KEY,
    name       TEXT UNIQUE NOT NULL,
    num_of_pos INT2        NULL CHECK (num_of_pos > 0 AND num_of_pos < 5),
    set_name   text        NULL REFERENCES unmatched.Set (name)
);
DROP TABLE IF EXISTS unmatched.Sidekick CASCADE;
CREATE TABLE unmatched.Sidekick
(
    id     SERIAL PRIMARY KEY,
    name   text UNIQUE NOT NULL,
    health int2        NOT NULL CHECK (health > 0),
    move   int2        NOT NULL CHECK (move > 0),
    attack text        NOT NULL CHECK (attack in ('Ranged', 'Melee'))
);

DROP TABLE IF EXISTS unmatched.Hero CASCADE;
CREATE TABLE unmatched.Hero
(
    id                  SERIAL PRIMARY KEY,
    name                text UNIQUE NOT NULL,
    health              int2        NOT NULL CHECK (health > 0),
    move                int2        NOT NULL CHECK (move > 0),
    ability             text        NOT NULL,
    attack              text        NOT NULL CHECK (attack in ('Ranged', 'Melee')),
    sidekick_name       text        NULL REFERENCES unmatched.Sidekick (name),
    number_of_sidekicks int2        NOT NULL DEFAULT 0,
    set_name            text        NULL REFERENCES unmatched.Set (name)
);

DROP TABLE IF EXISTS unmatched.Card CASCADE;
CREATE TABLE unmatched.Card
(
    id        SERIAL PRIMARY KEY,
    name      text NOT NULL,
    boost     int2 NULL CHECK (boost > 0),
    effect    text NULL,
    is_unique bool NOT NULL,
    UNIQUE (name, boost)
);

DROP TABLE IF EXISTS unmatched.SchemeCard;
CREATE TABLE unmatched.SchemeCard
(
    id        int2 REFERENCES unmatched.Card,
    name      text NOT NULL,
    boost     int2 NULL CHECK (boost > 0),
    effect    text NULL,
    is_unique bool NOT NULL
);

DROP TABLE IF EXISTS unmatched.AttackCard;
CREATE TABLE unmatched.AttackCard
(
    id        int2 REFERENCES unmatched.Card,
    name      text NOT NULL,
    value     int2 NOT NULL,
    boost     int2 NULL CHECK (boost > 0),
    effect    text NULL,
    is_unique bool NOT NULL
);

DROP TABLE IF EXISTS unmatched.VersatileCard;
CREATE TABLE unmatched.VersatileCard
(
    id        int2 REFERENCES unmatched.Card,
    name      text NOT NULL,
    value     int2 NOT NULL,
    boost     int2 NULL CHECK (boost > 0),
    effect    text NULL,
    is_unique bool NOT NULL
);

DROP TABLE IF EXISTS unmatched.DefenseCard;
CREATE TABLE unmatched.DefenseCard
(
    id        int2 REFERENCES unmatched.Card,
    name      text NOT NULL,
    value     int2 NOT NULL,
    boost     int2 NULL CHECK (boost > 0),
    effect    text NULL,
    is_unique bool NOT NULL
);

DROP TABLE IF EXISTS unmatched.HeroCard;
CREATE TABLE unmatched.HeroCard
(
    id          BIGINT PRIMARY KEY,
    hero_name   text REFERENCES unmatched.Hero (name),
    card_name   text NOT NULL,
    card_boost  int2 NOT NULL,
    card_number int2 NOT NULL,
    belong_to   text NOT NULL DEFAULT 'Any' CHECK (belong_to in ('Hero', 'Any', 'Sidekick')),
    FOREIGN KEY (card_name, card_boost) REFERENCES unmatched.Card(name, boost)
);

--VIEWS--
--1--
DROP VIEW IF EXISTS unmatched.HeroMapView;
CREATE VIEW unmatched.HeroMapView AS
SELECT hero.name as hero_name, map.name as map_name
FROM unmatched.Hero AS hero
         LEFT OUTER JOIN unmatched.Map as map ON hero.set_name = map.set_name;
SELECT *
FROM unmatched.HeroMapView;

--2--


--FUNCTIONS--
--1--
DROP FUNCTION unmatched.card_next_id();
CREATE OR REPLACE FUNCTION unmatched.card_next_id()
    RETURNS BIGINT AS
$$
DECLARE
    num BIGINT;
    max BIGINT;
BEGIN
    num := count(*) FROM unmatched.Card;
    IF num = 0 THEN
        RETURN 1;
    ELSE
        max = max(id) FROM unmatched.Card;
        RETURN max + 1;
    END IF;
END;
$$ LANGUAGE plpgsql;

--FUNCTIONS FOR TRIGGERS--
--1--
CREATE OR REPLACE FUNCTION unmatched.hero_delete_trigger_fnc()
    RETURNS TRIGGER AS
$$
BEGIN
    DELETE FROM unmatched.HeroCard WHERE HeroCard.hero_name = old.name;
    DELETE FROM unmatched.Sidekick where Sidekick.name = old.sidekick_name;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

--2--
CREATE OR REPLACE FUNCTION unmatched.card_insert_trigger_fnc()
    RETURNS TRIGGER AS
$$
BEGIN
    INSERT INTO unmatched.Card VALUES (new.id, new.name, new.boost, new.effect, new.is_unique);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

--6--
CREATE OR REPLACE FUNCTION unmatched.delete_card_trigger_fnc()
    RETURNS TRIGGER AS
$$
BEGIN
    DELETE FROM unmatched.HeroCard AS hero_card
    WHERE hero_card.card_name = old.name AND hero_card.card_boost = old.boost;
    DELETE FROM unmatched.Card AS card
    WHERE card.name = old.name AND card.boost = old.boost;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

--7--
CREATE OR REPLACE FUNCTION unmatched.set_delete_trigger_fnc()
    RETURNS TRIGGER AS
$$
BEGIN
    DELETE FROM unmatched.Hero WHERE Hero.set_name = old.name;
    DELETE FROM unmatched.Map WHERE Map.set_name = old.name;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


--9--
CREATE OR REPLACE FUNCTION unmatched.card_update_trigger_fnc()
    RETURNS TRIGGER AS
$$
BEGIN
    UPDATE unmatched.Card
    SET name      = new.name,
        is_unique = new.is_unique,
        boost     = new.boost
    WHERE card.id = new.id;

    UPDATE unmatched.HeroCard
    SET card_name = new.name
    WHERE card_name = old.name;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

--10--
CREATE OR REPLACE FUNCTION unmatched.hero_card_insert_trigger_fnc()
    RETURNS TRIGGER AS
$$
DECLARE
    num_card BIGINT;
    num_heroes BIGINT;
BEGIN
    num_card := count(*) FROM unmatched.Card as card
                WHERE card.name = new.card_name AND card.boost = new.card_boost;
    num_heroes := count(*) FROM unmatched.Hero as hero
                  WHERE hero.name = new.hero_name;
    IF num_card > 0 AND num_heroes > 0
    THEN
        RETURN NEW;
    ELSE
        RAISE NOTICE 'Can`t insert connection because not found hero or card in their tables';
    END IF;
END;
$$ LANGUAGE plpgsql;


--TRIGGERS--
--1--
DROP TRIGGER IF EXISTS herodelete ON unmatched.Hero;
CREATE TRIGGER HeroDelete
    AFTER DELETE
    ON unmatched.Hero
    FOR EACH ROW
EXECUTE PROCEDURE hero_delete_trigger_fnc();

--2--
DROP TRIGGER IF EXISTS attackCardInsert ON unmatched.Attackcard;
CREATE TRIGGER attackCardInsert
    BEFORE INSERT
    ON unmatched.AttackCard
    FOR EACH ROW
EXECUTE PROCEDURE card_insert_trigger_fnc();

--3--
DROP TRIGGER IF EXISTS defenseCardInsert ON unmatched.DefenseCard;
CREATE TRIGGER defenseCardInsert
    BEFORE INSERT
    ON unmatched.DefenseCard
    FOR EACH ROW
EXECUTE PROCEDURE card_insert_trigger_fnc();

--4--
DROP TRIGGER IF EXISTS versatileCardInsert ON unmatched.VersatileCard;
CREATE TRIGGER versatileCardInsert
    BEFORE INSERT
    ON unmatched.VersatileCard
    FOR EACH ROW
EXECUTE PROCEDURE card_insert_trigger_fnc();

--5--
DROP TRIGGER IF EXISTS schemeCardInsert ON unmatched.SchemeCard;
CREATE TRIGGER schemeCardInsert
    BEFORE INSERT
    ON unmatched.SchemeCard
    FOR EACH ROW
EXECUTE PROCEDURE card_insert_trigger_fnc();

--6--
DROP TRIGGER IF EXISTS deleteAttackCard ON unmatched.AttackCard;
CREATE TRIGGER deleteAttackCard
    AFTER DELETE
    ON unmatched.AttackCard
    FOR EACH ROW
EXECUTE PROCEDURE delete_card_trigger_fnc();

--7--
DROP TRIGGER IF EXISTS deleteDefenseCard ON unmatched.DefenseCard;
CREATE TRIGGER deleteDefenseCard
    AFTER DELETE
    ON unmatched.DefenseCard
    FOR EACH ROW
EXECUTE PROCEDURE delete_card_trigger_fnc();

--8--
DROP TRIGGER IF EXISTS deleteVersatileCard ON unmatched.VersatileCard;
CREATE TRIGGER deleteVersatileCard
    AFTER DELETE
    ON unmatched.VersatileCard
    FOR EACH ROW
EXECUTE PROCEDURE delete_card_trigger_fnc();

--9--
DROP TRIGGER IF EXISTS deleteSchemeCard ON unmatched.SchemeCard;
CREATE TRIGGER deleteSchemeCard
    AFTER DELETE
    ON unmatched.SchemeCard
    FOR EACH ROW
EXECUTE PROCEDURE delete_card_trigger_fnc();

--10--
DROP TRIGGER IF EXISTS deleteSet ON unmatched.Set;
CREATE TRIGGER deleteSet
    AFTER DELETE
    ON unmatched.Set
    FOR EACH ROW
EXECUTE PROCEDURE set_delete_trigger_fnc();

--11--
DROP TRIGGER IF EXISTS updateAttackCard ON unmatched.AttackCard;
CREATE TRIGGER updateAttackCard
    AFTER UPDATE
    ON unmatched.AttackCard
    FOR EACH ROW
EXECUTE PROCEDURE card_update_trigger_fnc();

--12--
DROP TRIGGER IF EXISTS updateDefenseCard ON unmatched.DefenseCard;
CREATE TRIGGER updateDefenseCard
    AFTER UPDATE
    ON unmatched.DefenseCard
    FOR EACH ROW
EXECUTE PROCEDURE card_update_trigger_fnc();

--13--
DROP TRIGGER IF EXISTS updateSchemeCard ON unmatched.SchemeCard;
CREATE TRIGGER updateSchemeCard
    AFTER UPDATE
    ON unmatched.SchemeCard
    FOR EACH ROW
EXECUTE PROCEDURE card_update_trigger_fnc();

--14--
DROP TRIGGER IF EXISTS updateVersatileCard ON unmatched.VersatileCard;
CREATE TRIGGER updateVersatileCard
    AFTER UPDATE
    ON unmatched.VersatileCard
    FOR EACH ROW
EXECUTE PROCEDURE card_update_trigger_fnc();

--15--
DROP TRIGGER IF EXISTS insertHeroCard ON unmatched.HeroCard;
CREATE TRIGGER insertHeroCard
    BEFORE INSERT
    ON unmatched.HeroCard
    FOR EACH ROW
EXECUTE PROCEDURE hero_card_insert_trigger_fnc();

--INSERTS FOR TESTS
--SETS--
INSERT INTO unmatched.Set (id, name, number_of_heroes)
VALUES (1, 'Robin hood VS Bigfoot', 2);

INSERT INTO unmatched.Set (id, name, number_of_heroes)
VALUES (2, 'Tales to amaze', 4);

--MAPS--
INSERT INTO unmatched.Map (id, name, num_of_pos, set_name)
VALUES (1, 'Yukon', 2, 'Robin hood VS Bigfoot');

INSERT INTO unmatched.Map (id, name, num_of_pos, set_name)
VALUES (2, 'Sherwood forest', 2, 'Robin hood VS Bigfoot');

--SIDEKICKS--
INSERT INTO unmatched.Sidekick (id, name, health, move, attack)
VALUES (1, 'Jackalope', 6, 3, 'Melee');

--HEROES--
INSERT INTO unmatched.Hero (id, name, health, move, ability, attack, sidekick_name, number_of_sidekicks, set_name)
VALUES (1, 'Bigfoot', 16, 3, 'When you end your turn in zone where are no opposing fighters, yo may draw a card',
        'Melee', 'Jackalope', 1, 'Robin hood VS Bigfoot');

--CARDS
INSERT INTO unmatched.DefenseCard (id, name, value, boost, effect, is_unique)
VALUES (card_next_id(), 'Bewilderment', 0, 2, 'Ignore all combat damage. Place your fighter in any space', false);

INSERT INTO unmatched.AttackCard (id, name, value, boost, effect, is_unique)
VALUES (card_next_id(), 'Disarming shot', 4, 3,
        'After combat: Draw a number of cards equal to the amount of damage dealt to the opposing fighter.', false);

INSERT INTO unmatched.VersatileCard (id, name, value, boost, effect, is_unique)
VALUES (card_next_id(), 'Wily fighting', 3, 1, 'If the opposing fighter is adjacent, do 1 damage to this fighter',
        false);

INSERT INTO unmatched.AttackCard (id, name, value, boost, is_unique)
VALUES (card_next_id(), 'Larger than life', 6, 3, true);

INSERT INTO unmatched.AttackCard(id, name, value, boost, effect, is_unique)
VALUES (card_next_id(), 'Disengage', 4, 2, 'Place your fighter in any space of his zone', false);

--HEROCARD--
INSERT INTO unmatched.HeroCard (id, hero_name, card_name, card_boost, belong_to, card_number)
VALUES (1, 'Bigfoot', 'Larger than life', 3, 'Hero', 3);

INSERT INTO unmatched.HeroCard (id, hero_name, card_name, card_boost, belong_to, card_number)
VALUES (2, 'Bigfoot', 'Disengage', 2, 'Any', 3);

--DELETE FROM unmatched.Hero WHERE name = 'Bigfoot';
-- DELETE
-- FROM unmatched.AttackCard
-- WHERE id = 153;

DELETE FROM unmatched.AttackCard
WHERE name = 'Larger than life' AND boost = 3;

UPDATE unmatched.AttackCard
SET name = 'Disengage'
WHERE id = 5;