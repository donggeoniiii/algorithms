SELECT
    FOOD_TYPE,
    REST_ID,
    REST_NAME,
    FAVORITES
FROM
    REST_INFO
WHERE
    (FOOD_TYPE, FAVORITES) IN (
        SELECT
            FOOD_TYPE,
            MAX(FAVORITES) AS FAVORITES
        FROM
            REST_INFO
        GROUP BY
            1
    )
ORDER BY
    1 DESC;