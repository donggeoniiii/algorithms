-- 코드를 작성해주세요
SELECT A.ID
FROM ECOLI_DATA A
    JOIN ECOLI_DATA B
    ON A.PARENT_ID = B.ID
    JOIN ECOLI_DATA C
    ON B.PARENT_ID = C.ID 
WHERE C.PARENT_ID IS NULL -- 3세대 대장균: 할아버지가 1세대
ORDER BY A.ID;