-- liquibase formatted sql
-- changeset uploadimage:create_db_uploadimage.sql
-- preconditions onFail:MARK_RAN onError:WARN

--
-- Structure for table uploadimage_options
--

DROP TABLE IF EXISTS uploadimage_options;
CREATE TABLE uploadimage_options (
id_options int NOT NULL,
strict SMALLINT NOT NULL,
responsive SMALLINT NOT NULL,
checkimageorigin SMALLINT NOT NULL,
modal SMALLINT NOT NULL,
guides SMALLINT NOT NULL,
highlight SMALLINT NOT NULL,
background SMALLINT NOT NULL,
autocrop SMALLINT NOT NULL,
dragcrop SMALLINT NOT NULL,
movable SMALLINT NOT NULL,
rotatable SMALLINT NOT NULL,
zoomable SMALLINT NOT NULL,
touchdragzoom SMALLINT NOT NULL,
mousewheelzoom SMALLINT NOT NULL,
cropboxmovable SMALLINT NOT NULL,
cropboxresizable SMALLINT NOT NULL,
doubleclicktoggle SMALLINT NOT NULL,
width int DEFAULT 0 NOT NULL,
height int DEFAULT 0 NOT NULL,
x int DEFAULT 0 NOT NULL,
y int DEFAULT 0 NOT NULL,
ratio varchar(50) DEFAULT '' NOT NULL,
fieldName varchar(50) DEFAULT '' NOT NULL,
PRIMARY KEY (id_options)
);
