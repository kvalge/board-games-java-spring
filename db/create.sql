CREATE TABLE product
(
    id           BIGSERIAL primary key not null,
    product_name VARCHAR(250)          not null,
    price        REAL                  not null,
    amount       INTEGER               not null
);

CREATE TABLE product_order
(
    id                BIGSERIAL primary key not null,
    product_id        BIGINT references product (id),
--     product_price     REAL references product (price),
    customer          VARCHAR(250)          not null,
    quantity          INTEGER               not null,
    order_total_price REAL                  not null,
    order_date        DATE                  not null,
    deadline          DATE                  not null,
    status            VARCHAR(25)           not null
);

