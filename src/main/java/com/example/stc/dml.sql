SELECT
    u.* ,
    t.*
FROM
    User u,
    Training_details t
WHERE
        u.user_id = t.user_id
GROUP BY username, tranning_date
HAVING COUNT(*) > 1
ORDER BY u.username, t.tranning_date DESC
