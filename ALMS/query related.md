```	
select * from wrs_user as wu join attendance_records  as ar on wu.id = ar.employee_id where ar.attendance_date>='2023-01-01' and ar.attendance_date<='2023-01-31' and wu.relieving_date>='2023-02-01' and ar.is_active=true
```

