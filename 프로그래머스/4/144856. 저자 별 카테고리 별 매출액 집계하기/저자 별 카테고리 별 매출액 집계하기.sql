WITH JANUARY_BOOK_SALES AS (
    SELECT 
        BOOK_ID, SUM(SALES) AS SALES
    FROM
        BOOK_SALES
    WHERE
        YEAR(SALES_DATE) = 2022 AND MONTH(SALES_DATE) = 1
    GROUP BY 
        1
)

SELECT 
    B.AUTHOR_ID, 
    AUTHOR_NAME, 
    CATEGORY, 
    SUM(PRICE * SALES) AS TOTAL_SALES
FROM 
    BOOK B
JOIN
    JANUARY_BOOK_SALES S
ON 
    B.BOOK_ID = S.BOOK_ID
JOIN
    AUTHOR A
ON 
    B.AUTHOR_ID = A.AUTHOR_ID
GROUP BY
    2, 3
ORDER BY
    1, 3 DESC;