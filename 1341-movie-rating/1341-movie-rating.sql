# Write your MySQL query statement below
-- Find user with most ratings (ties: lexicographically smaller name)
(
    SELECT u.name AS results
    FROM Users u
    JOIN (
        SELECT user_id, COUNT(*) AS cnt
        FROM MovieRating
        GROUP BY user_id
    ) r ON u.user_id = r.user_id
    ORDER BY r.cnt DESC, u.name ASC
    LIMIT 1
)

UNION ALL

-- Find movie with highest average rating in February 2020 (ties: lexicographically smaller title)
(
    SELECT m.title AS results
    FROM Movies m
    JOIN (
        SELECT movie_id, AVG(rating) AS avg_rating
        FROM MovieRating
        WHERE created_at BETWEEN '2020-02-01' AND '2020-02-29'
        GROUP BY movie_id
    ) mr ON m.movie_id = mr.movie_id
    ORDER BY mr.avg_rating DESC, m.title ASC
    LIMIT 1
);