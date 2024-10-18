SELECT E.EMP_NO, 
       EMP_NAME, 
       IF(SUM(SCORE) / 2 < 80, 'C', 
          IF(SUM(SCORE) / 2 < 90, 'B', 
             IF(SUM(SCORE) / 2 < 96, 'A', 'S'))) AS GRADE, 
       SAL * IF(SUM(SCORE) / 2 < 80, 0, 
                IF(SUM(SCORE) / 2 < 90, 10,
                   IF(SUM(SCORE) / 2 < 96, 15, 20))) / 100 AS BONUS
FROM HR_EMPLOYEES E
    JOIN HR_GRADE G
    ON E.EMP_NO = G.EMP_NO
GROUP BY 1
ORDER BY 1;