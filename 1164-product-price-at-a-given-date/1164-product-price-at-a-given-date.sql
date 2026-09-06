# Write your MySQL query statement below
SELECT 
    product_id,
    IFNULL(price, 10) AS price
FROM (
    SELECT 
        product_id,
        new_price AS price,
        ROW_NUMBER() OVER (
            PARTITION BY product_id 
            ORDER BY change_date DESC
        ) AS rn
    FROM Products
    WHERE change_date <= '2019-08-16'
) t
RIGHT JOIN (
    SELECT DISTINCT product_id FROM Products
) p USING (product_id)
WHERE rn = 1 OR rn IS NULL;