-- 코드를 입력하세요
SELECT DATE_FORMAT(SALES_DATE, '%Y-%m-%d') as SALES_DATE, PRODUCT_ID, USER_ID, SALES_AMOUNT
FROM ONLINE_SALE
WHERE MONTH(SALES_DATE) = 3

union all

SELECT DATE_FORMAT(SALES_DATE, '%Y-%m-%d') as SALES_DATE, PRODUCT_ID, null, SALES_AMOUNT
FROM OFFLINE_SALE
WHERE MONTH(SALES_DATE) = 3

ORDER BY SALES_DATE asc, PRODUCT_ID asc, USER_ID asc;