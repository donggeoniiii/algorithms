WITH FIRST_GEN AS (
    SELECT *
    FROM
        ECOLI_DATA
    WHERE
        PARENT_ID IS NULL
)
     
SELECT 
    T.ID
FROM
    ECOLI_DATA T
WHERE
    T.PARENT_ID IN (
        SELECT 
            S.ID
        FROM 
            ECOLI_DATA S
        JOIN 
            FIRST_GEN F
        ON 
            S.PARENT_ID = F.ID
    )
ORDER BY
    1 ASC;