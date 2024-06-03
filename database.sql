use void;

CREATE TABLE categories
(
    id   BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
) ENGINE = InnoDB;

CREATE TABLE products
(
    id          BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(255) NOT NULL,
    price       BIGINT       NOT NULL,
    category_id BIGINT       NOT NULL,
    FOREIGN KEY fk_products_categories (category_id) REFERENCES categories (id)
) ENGINE = InnoDB;

ALTER TABLE categories
    ADD COLUMN created_date       TIMESTAMP,
    ADD COLUMN last_modified_date TIMESTAMP;