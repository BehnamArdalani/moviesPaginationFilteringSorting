CREATE TABLE movie
(
    id              UUID NOT NULL,
    title           TEXT NOT NULL,
    description     TEXT,
    genre           VARCHAR(255) NOT NULL,
    rating          FLOAT,
    release_date    TIMESTAMP,
    PRIMARY KEY (id)
);
