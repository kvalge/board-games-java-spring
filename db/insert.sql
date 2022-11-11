INSERT INTO product (id, product_name, price, amount)
VALUES (DEFAULT, 'Chess', 24.99, 999);

INSERT INTO product (id, product_name, price, amount)
VALUES (DEFAULT, 'Checkers ', 13.99, 250);

INSERT INTO product (id, product_name, price, amount)
VALUES (DEFAULT, 'Dominoes', 19.99, 400);

INSERT INTO product (id, product_name, price, amount)
VALUES (DEFAULT, 'Puzzle Game of Thrones', 7.99, 200);

INSERT INTO product (id, product_name, price, amount)
VALUES (DEFAULT, 'Puzzle Queen´s Gambit', 12.99, 200);

INSERT INTO product (id, product_name, price, amount)
VALUES (DEFAULT, 'Deal with the Devil', 11.99, 200);

INSERT INTO product (id, product_name, price, amount)
VALUES (DEFAULT, 'Battleship', 7.99, 100);

INSERT INTO product (id, product_name, price, amount)
VALUES (DEFAULT, 'Monopoly', 11.99, 300);



INSERT INTO product_order (id, customer, product_id, quantity, order_total_price, order_date, deadline, status)
VALUES (DEFAULT, 'Kati Karu', 35, 1, 24.99, '2022-11-08', '2022-11-11', 'In process');

INSERT INTO product_order (id, customer, product_id, quantity, order_total_price, order_date, deadline, status)
VALUES (DEFAULT, 'Kati Karu', 36, 1, 13.99, '2022-11-08', '2022-11-11', 'Order delivered');

INSERT INTO product_order (id, customer, product_id, quantity, order_total_price, order_date, deadline, status)
VALUES (DEFAULT, 'Juta Jänese', 37, 1, 24.99, '2022-11-09', '2022-11-14', 'In process');

INSERT INTO product_order (id, customer, product_id, quantity, order_total_price, order_date, deadline, status)
VALUES (DEFAULT, 'Juta Jänese', 38, 2, 24.99, '2022-11-09', '2022-11-14', 'In process');

INSERT INTO product_order (id, customer, product_id, quantity, order_total_price, order_date, deadline, status)
VALUES (DEFAULT, 'Rein Rebase', 39, 2, 24.99, '2022-11-11', '2022-11-16', 'In process');



