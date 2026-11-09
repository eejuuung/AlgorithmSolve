-- 코드를 입력하세요
SELECT CONCAT('/home/grep/src/',
              ugb.BOARD_ID, '/',
             ugf.FILE_ID,ugf.FILE_NAME,ugf.FILE_EXT) AS 'FILE_PATH'
FROM USED_GOODS_BOARD ugb
JOIN USED_GOODS_FILE ugf ON (ugb.BOARD_ID = ugf.BOARD_ID)
WHERE ugb.VIEWS = 
    (
        SELECT MAX(VIEWS)
        FROM USED_GOODS_BOARD
    )
ORDER BY ugf.FILE_ID DESC;