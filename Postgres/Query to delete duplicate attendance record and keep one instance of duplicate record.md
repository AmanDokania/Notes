SELECT employee_id, attendance_date, wu.email, COUNT(*)
FROM attendance_records ar
join wrs_user wu on wu.id= ar.employee_id
WHERE
attendance_date>='2024-01-01' and attendance_date<='2024-01-31' and ar.is_active = true
GROUP BY employee_id, attendance_date, wu.email
HAVING COUNT(*) >= 2 ORDER BY employee_id, attendance_date;


DELETE FROM attendance_records
WHERE (employee_id, attendance_date,id) IN (
    SELECT 
        employee_id, 
        attendance_date,
		id
    FROM 
        (
            SELECT 
                ar.employee_id, 
				ar.id,
                ar.attendance_date, 
                wu.email,
                ROW_NUMBER() OVER(PARTITION BY ar.employee_id, ar.attendance_date ORDER BY ar.employee_id, ar.attendance_date) AS row_num,
                COUNT(*) OVER(PARTITION BY ar.employee_id, ar.attendance_date) as cnt
            FROM 
                attendance_records ar
            JOIN 
                wrs_user wu ON wu.id = ar.employee_id
            WHERE
                ar.attendance_date >= '2024-01-01' 
                AND ar.attendance_date <= '2024-01-31' 
                AND ar.is_active = true
        ) AS subquery
    WHERE 
        (cnt = 3 AND row_num > 1)
);