--Write an SQL query that reports all product names of the products in the
--Sales table along with their selling year and price.

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


SELECT p.product_name, s.year, s.price FROM Sales s LEFT JOIN Products p ON s.product_id = p.product_id;

+--------------+------+-------+
| product_name | year | price |
+--------------+------+-------+
| Nokia        | 2008 |  5000 |
| Nokia        | 2009 |  5000 |
| Apple        | 2011 |  9000 |
+--------------+------+-------+