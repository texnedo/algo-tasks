--Write an SQL query that selects the product id, year, quantity,
--and price for the first year of every product sold.

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


SELECT r.product_id, r.first_year, s.quantity, s.price
    FROM (SELECT product_id, min(year) as first_year FROM Sales GROUP BY product_id) as r
        LEFT JOIN Sales s ON (s.product_id = r.product_id AND r.first_year = s.year)


+------------+------------+----------+-------+
| product_id | first_year | quantity | price |
+------------+------------+----------+-------+
|        100 |       2008 |       10 |  5000 |
|        200 |       2011 |       15 |  9000 |
+------------+------------+----------+-------+