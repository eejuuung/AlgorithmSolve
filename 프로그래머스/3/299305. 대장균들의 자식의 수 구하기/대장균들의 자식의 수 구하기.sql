-- 코드를 작성해주세요
SELECT 
    ed.ID, 
    (
        SELECT COUNT(*)
        FROM ECOLI_DATA
        WHERE PARENT_ID = ed.ID
    ) AS 'CHILD_COUNT'
FROM ECOLI_DATA ed
ORDER BY ID ASC;