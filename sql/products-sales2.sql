--Write an SQL query that reports the total quantity sold for every product id.

CREATE TABLE Sales (sale_id int, product_id int, year int, quantity int, price int);

CREATE TABLE Products (product_id int, product_name VARCHAR(300));


INSERT INTO
    Sales(sale_id, product_id, year, quantity, price)
VALUES
    (1        , 100         , 2008  , 10        , 5000),
    (2        , 100         , 2009  , 12        , 5000),
    (7        , 200         , 2011  , 15        , 9000);


INSERT INTO
    Products (product_id, product_name)
VALUES
    (100        , 'Nokia'),
    (200        , 'Apple'),
    ( 300        , 'Samsung'  );

SELECT p.product_name, r.total_quantity FROM (SELECT product_id, sum(quantity) as total_quantity
FROM Sales GROUP BY product_id) as r LEFT JOIN Products p ON r.product_id = p.product_id;
+--------------+----------------+
| product_name | total_quantity |
+--------------+----------------+
| Nokia        |             22 |
| Apple        |             15 |
+--------------+----------------+