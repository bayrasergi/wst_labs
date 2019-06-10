create table ship
(
  id     bigint primary key,
  name   varchar(50)  not null,
  type   varchar(100) not null,
  rarity varchar(50)  not null,
  nation varchar(200) not null,
  level  integer      not null default 1
);

create sequence ship_sequence start with 11;
alter table ship alter column id set default nextval('ship_sequence');

INSERT INTO public.ship
(id, "name", "type", rarity, nation, "level")
VALUES(1, 'Avrora', 'Light Cruiser', 'Super Rare', 'North Union', 34);

INSERT INTO public.ship
(id, "name", "type", rarity, nation, "level")
VALUES(2, 'Belfast', 'Light Cruiser', 'Super Rare', 'Royal Navy', 15);

INSERT INTO public.ship
(id, "name", "type", rarity, nation, "level")
VALUES(3, 'Hood', 'Battlecruiser', 'Super Rare', 'Royal Navy', 70);

INSERT INTO public.ship
(id, "name", "type", rarity, nation, "level")
VALUES(4, 'Leander', 'Light Cruiser', 'Common', 'Royal Navy', 50);

INSERT INTO public.ship
(id, "name", "type", rarity, nation, "level")
VALUES(5, 'Cassin', 'Destroyer', 'Common', 'Eagle Union', 56);

INSERT INTO public.ship
(id, "name", "type", rarity, nation, "level")
VALUES(6, 'Downes', 'Destroyer', 'Common', 'Eagle Union', 59);

INSERT INTO public.ship
(id, "name", "type", rarity, nation, "level")
VALUES(7, 'Portland', 'Heavy Cruiser', 'Rare', 'Eagle Union', 10);

INSERT INTO public.ship
(id, "name", "type", rarity, nation, "level")
VALUES(8, 'San Diego', 'Light Cruiser', 'Ultra Rare', 'Eagle Union', 1);

INSERT INTO public.ship
(id, "name", "type", rarity, nation, "level")
VALUES(9, 'Kaga', 'Aircraft Carrier', 'Super Rare', 'Sakura Empire', 70);

INSERT INTO public.ship
(id, "name", "type", rarity, nation, "level")
VALUES(10, 'Akagi', 'Aircraft Carrier', 'Super Rare', 'Sakura Empire', 70);