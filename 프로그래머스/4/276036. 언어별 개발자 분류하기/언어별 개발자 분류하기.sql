WITH FRONTEND AS (
    SELECT SUM(CODE) CODE
    FROM SKILLCODES
    WHERE CATEGORY = 'FRONT END'
),
CSHARP AS (
    SELECT CODE
    FROM SKILLCODES
    WHERE NAME = 'C#'
),
PYTHON AS (
    SELECT CODE
    FROM SKILLCODES
    WHERE NAME = 'PYTHON'
)

SELECT 
    CASE 
        WHEN 
            D.SKILL_CODE & P.CODE = P.CODE
            AND D.SKILL_CODE & F.CODE > 0 
            -- Front End + Python
        THEN 
            'A'
        WHEN
            D.SKILL_CODE & C.CODE = C.CODE 
            -- C#
        THEN 
            'B'
        WHEN 
            D.SKILL_CODE & F.CODE > 0 
            -- Front End 
        THEN 
            'C'
        ELSE
            NULL
    END GRADE,
    ID,
    EMAIL
FROM 
    DEVELOPERS D, FRONTEND F, CSHARP C, PYTHON P
HAVING 
    GRADE IS NOT NULL
ORDER BY
    1, 2;

