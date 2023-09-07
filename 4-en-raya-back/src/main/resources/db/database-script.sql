CREATE DATABASE IF NOT EXISTS cuatro_en_raya;

USE cuatro_en_raya;
GRANT ALL PRIVILEGES ON *.* TO 'user'@'%';

CREATE TABLE IF NOT EXISTS player (

    id INTEGER,
    player_name CHAR(25) NOT NULL

);