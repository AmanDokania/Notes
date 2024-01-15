1. when we are creating unique constraint on attendance_record table so in case of null values unique contraint is not working because postgres treat null values as distinct
2. example  (1,2,null) and (1,2,null) 
		1. both rows consider as distinct because of null value so we can add both records in table

3. possible solutions are
```  postgres
CREATE UNIQUE INDEX unique_attendance_record  ON attendance_records (employee_id, leave_request_id,COALESCE(out_comment,''));
```
https://stackoverflow.com/questions/8289100/create-unique-constraint-with-null-columns
we can create indexes using above query and for null colums we can put a unique value which we will  never  insert into table.



