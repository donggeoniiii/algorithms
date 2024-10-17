-- 코드를 작성해주세요
SELECT O.ID, IF (PER <= 0.25, 'CRITICAL', 
              IF (PER <= 0.50, 'HIGH',
                 IF (PER <= 0.75, 'MEDIUM', 'LOW'))) AS COLONY_NAME
FROM ECOLI_DATA O
    JOIN (SELECT ID, PERCENT_RANK() OVER (ORDER BY SIZE_OF_COLONY DESC) AS PER
         FROM ECOLI_DATA) N
    ON O.ID = N.ID
ORDER BY O.ID;


