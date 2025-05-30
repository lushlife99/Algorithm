SELECT ID,
       CASE
           WHEN RN <= TOTAL * 0.25 THEN 'LOW'
           WHEN RN <= TOTAL * 0.50 THEN 'MEDIUM'
           WHEN RN <= TOTAL * 0.75 THEN 'HIGH'
           ELSE 'CRITICAL'
       END AS COLONY_NAME
FROM (
    SELECT ID,
           SIZE_OF_COLONY,
           ROW_NUMBER() OVER (ORDER BY SIZE_OF_COLONY) AS RN,
           COUNT(*) OVER () AS TOTAL
    FROM ECOLI_DATA
) AS RANKED
ORDER BY ID;
