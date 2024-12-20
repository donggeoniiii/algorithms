WITH MAX_DIFFERENTIATION_SIZE_BY_YEAR 
AS (
    SELECT 
        YEAR(DIFFERENTIATION_DATE) AS YEAR,
        MAX(SIZE_OF_COLONY) AS SIZE_OF_COLONY
    FROM
        ECOLI_DATA
    GROUP BY
        1
)

SELECT
    YEAR(DIFFERENTIATION_DATE) AS YEAR,
    Y.SIZE_OF_COLONY - E.SIZE_OF_COLONY AS YEAR_DEV,
    E.ID
FROM
    ECOLI_DATA E
JOIN
    MAX_DIFFERENTIATION_SIZE_BY_YEAR Y
ON
    YEAR(DIFFERENTIATION_DATE) = Y.YEAR
ORDER BY
    1, 2;

    