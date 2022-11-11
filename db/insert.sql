INSERT INTO product (id, productName, price, amount)
VALUES (DEFAULT, 'Chess', 24.99, 999);

INSERT INTO product (id, productName, price, amount)
VALUES (DEFAULT, 'Checkers ', 13.99, 250);

INSERT INTO product (id, productName, price, amount)
VALUES (DEFAULT, 'Dominoes', 19.99, 400);

INSERT INTO product (id, productName, price, amount)
VALUES (DEFAULT, 'Pusle Game of Thrones', 7.99, 200);

INSERT INTO product (id, productName, price, amount)
VALUES (DEFAULT, 'Pusle Queen´s Gambit', 12.99, 200);

INSERT INTO product (id, productName, price, amount)
VALUES (DEFAULT, 'Deal with the Devil', 11.99, 200);

INSERT INTO product (id, productName, price, amount)
VALUES (DEFAULT, 'Battleship', 7.99, 100);

INSERT INTO product (id, productName, price, amount)
VALUES (DEFAULT, 'Monopoly', 11.99, 300);



INSERT INTO product_order (id, customer, product_id, quantity, orderTotalPrice, orderDate, deadline, status)
VALUES (DEFAULT, 'Kati Karu', 'Chess', 1, 24.99, '2022-11-08', '2022-11-11', 'In process');

INSERT INTO product_order (id, customer, product_id, quantity, orderTotalPrice, orderDate, deadline, status)
VALUES (DEFAULT, 'Kati Karu', 'Checkers', 1, 13.99, '2022-11-08', '2022-11-11', 'Completed');

INSERT INTO product_order (id, customer, product_id, quantity, orderTotalPrice, orderDate, deadline, status)
VALUES (DEFAULT, 'Juta Jänese', 'Chess', 1, 24.99, '2022-11-09', '2022-11-14', 'In process');



