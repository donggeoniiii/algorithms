SELECT 
    I1.FOOD_TYPE, I1.REST_ID, I1.REST_NAME, I1.FAVORITES
FROM 
    REST_INFO I1
JOIN
    (SELECT 
        FOOD_TYPE, MAX(FAVORITES) AS FAVORITES
     FROM 
        REST_INFO 
     GROUP BY 
        FOOD_TYPE
    ) I2
ON 
    I1.FOOD_TYPE = I2.FOOD_TYPE AND I1.FAVORITES = I2.FAVORITES
ORDER BY
    1 DESC;
