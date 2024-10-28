--changeset xxSlashxx:1

CREATE TABLE cart (
    id INT PRIMARY KEY,
    user_id INT NOT NULL UNIQUE
);

CREATE SEQUENCE cart_id_seq;

CREATE TABLE cart_item (
    id INT PRIMARY KEY,
    cart_id INT NOT NULL,
    product_id INT NOT NULL,
    UNIQUE(cart_id, product_id)
);

CREATE SEQUENCE cart_item_id_seq;
