CREATE TABLE "Card"
(
    name      text NOT NULL,
    val     int2 NULL,
    boost     int2 NOT NULL,
    effect    text NULL,
    uniq bool NOT NULL,
    type text NOT NULL,
    PRIMARY KEY (name, boost)
);

CREATE TABLE "Set"
(
    name             TEXT PRIMARY KEY,
    number_of_heroes int2        NOT NULL,
    number_of_maps  int2 NULL
);

--DROP TABLE IF EXISTS unmatched.Map CASCADE;
CREATE TABLE "Map"
(
    name       TEXT PRIMARY KEY,
    num_of_pos INT2        NULL,
    set_name   text        NULL REFERENCES "Set" (name)
);

--DROP TABLE IF EXISTS unmatched.Sidekick CASCADE;
CREATE TABLE "Sidekick"
(
    name   text PRIMARY KEY,
    health int2        NOT NULL,
    move   int2        NOT NULL,
    attack text        NOT NULL,
    number_of_sidekicks int2        NOT NULL
);

--DROP TABLE IF EXISTS unmatched.Hero CASCADE;
CREATE TABLE "Hero"
(
    name                text PRIMARY KEY,
    health              int2        NOT NULL,
    move                int2        NOT NULL,
    ability             text        NOT NULL,
    attack              text        NOT NULL,
    sidekick_name       text        NULL REFERENCES "Sidekick" (name),
    set_name            text        NULL REFERENCES "Set" (name)
);

--DROP TABLE IF EXISTS unmatched.HeroCard;
CREATE TABLE "HeroCard"
(
    id          BIGINT PRIMARY KEY,
    hero_name   text REFERENCES "Hero" (name),
    card_name   text NOT NULL,
    card_boost  int2 NOT NULL,
    card_number int2 NOT NULL,
    belong   text NOT NULL,
    FOREIGN KEY (card_name, card_boost) REFERENCES "Card"(name, boost)
);

--INSERTS FOR TESTS--
--SETS--
INSERT INTO "Set" (name, number_of_heroes, number_of_maps)
VALUES ('Robin hood VS Bigfoot', 2, 2);

INSERT INTO "Set" (name, number_of_heroes, number_of_maps)
VALUES ('Tales to amaze', 4, 4);

INSERT INTO "Set" (name, number_of_heroes, number_of_maps)
VALUES ('Battle of legends: Volume 1', 4, 2);

--MAPS--

INSERT INTO "Map" (name, num_of_pos, set_name)
VALUES ('Sherwood forest', 2, 'Robin hood VS Bigfoot');

-- --SIDEKICKS--
-- INSERT INTO "Sidekick" (name, health, move, attack, number_of_sidekicks)
-- VALUES ('Jackalope', 6, 3, 'Melee', 1);
--
-- --HEROES--
-- INSERT INTO "Hero" (name, health, move, ability, attack, sidekick_name, set_name)
-- VALUES ('Bigfoot', 16, 3, 'When you end your turn in zone where are no opposing fighters, yo may draw a card',
--         'Melee', 'Jackalope', 'Robin hood VS Bigfoot');
--
-- --CARDS
-- INSERT INTO "Card" (name, val, boost, effect, uniq, type)
-- VALUES ('Bewilderment', 0, 2, 'Ignore all combat damage. Place your fighter in any space', false, 'Defense');
--
-- INSERT INTO "Card" (name, val, boost, effect, uniq, type)
-- VALUES ('Disarming shot', 4, 3,
--         'After combat: Draw a number of cards equal to the amount of damage dealt to the opposing fighter.', false, 'Attack');
--
-- INSERT INTO "Card" (name, val, boost, effect, uniq, type)
-- VALUES ('Wily fighting', 3, 1, 'If the opposing fighter is adjacent, do 1 damage to this fighter', false, 'Versatile');
--
-- INSERT INTO "Card" (name, val, boost, uniq, type)
-- VALUES ('Larger than life', 6, 3, true, 'Attack');
--
-- INSERT INTO "Card" (name, val, boost, effect, uniq, type)
-- VALUES ('Disengage', 4, 2, 'After combat: Place your fighter in any space of his zone', false, 'Attack');
--
-- --HEROCARD--
-- INSERT INTO "HeroCard" (id, hero_name, card_name, card_boost, belong, card_number)
-- VALUES (1, 'Bigfoot', 'Larger than life', 3, 'Hero', 3);
--
-- INSERT INTO "HeroCard" (id, hero_name, card_name, card_boost, belong, card_number)
-- VALUES (2, 'Bigfoot', 'Disengage', 2, 'Any', 3);
