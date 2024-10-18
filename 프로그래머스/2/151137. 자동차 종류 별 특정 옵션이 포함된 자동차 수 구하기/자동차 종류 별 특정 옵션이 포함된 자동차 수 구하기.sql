SELECT 
    CAR_TYPE, COUNT(*) AS CARS
FROM 
    (SELECT
        *
    FROM 
        CAR_RENTAL_COMPANY_CAR
    WHERE 
        OPTIONS LIKE '%열선%'
    OR OPTIONS LIKE '%통풍%'
    OR OPTIONS LIKE '%가죽%') AS A
GROUP BY 
    1
ORDER BY 
    1;