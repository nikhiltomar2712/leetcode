# Write your MySQL query statement below
WITH RankedEmployees AS (
    SELECT 
        e.name,
        e.salary,
        e.departmentId,
        d.name AS department_name,
        DENSE_RANK() OVER (PARTITION BY e.departmentId ORDER BY e.salary DESC) AS rnk
    FROM Employee e
    JOIN Department d ON e.departmentId = d.id
)
SELECT 
    department_name AS Department,
    name AS Employee,
    salary AS Salary
FROM RankedEmployees
WHERE rnk = 1;