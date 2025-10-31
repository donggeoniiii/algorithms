WITH RECURSIVE ECOLI_GEN AS (
    SELECT 
        ID,
        PARENT_ID,
        1 AS GENERATION
    FROM
        ECOLI_DATA
    WHERE
        PARENT_ID IS NULL
    
    UNION ALL
    
    SELECT
        E.ID,
        E.PARENT_ID,
        G.GENERATION + 1 AS GENERATION
    FROM
        ECOLI_DATA E
    JOIN   
        ECOLI_GEN G
    ON 
        E.PARENT_ID = G.ID
)
     
SELECT 
    ID
FROM
    ECOLI_GEN 
WHERE
    GENERATION = 3 -- 3세대
ORDER BY
    1 ASC;