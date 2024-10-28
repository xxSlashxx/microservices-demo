--changeset xxSlashxx:1

CREATE TABLE product (
    id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price NUMERIC(8,2) NOT NULL
);

CREATE SEQUENCE product_id_seq;