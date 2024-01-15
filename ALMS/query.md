ALTER TABLE attendance_records
ADD CONSTRAINT unique_attendance_record 
UNIQUE (employee_id, in_time, out_time, attendance_type_id, leave_request_id, elective_holiday_id, compensatory_off_id, is_active, is_lwp, attendance_date);

DELETE FROM public.attendance_records;

EXPLAIN SELECT DISTINCT employee_id, leave_request_id,in_time from attendance_records;

CREATE UNIQUE INDEX unique_attendance_record  ON attendance_records (employee_id, leave_request_id,COALESCE(out_comment,''));

SELECT * FROM pg_indexes WHERE tablename = 'attendance_records';

SELECT * FROM public.attendance_records
ORDER BY id ASC ;

INSERT INTO public.attendance_records(
     employee_id, in_time, out_time, in_comment, out_comment, attendance_type_id, leave_request_id, elective_holiday_id, compensatory_off_id, organization_id, organization_uuid, created_at, created_by_id, updated_at, updated_by_id, is_active, is_marked_by_aur, is_lwp, attendance_date)
    VALUES (7732,NULL,NULL, 'Attendance Record created when a leave request of Past Date is Approved.',NULL,NULL,2,NULL,NULL,1,'49d65aeb-faaf-4a8e-9fca-763ff3b12d78', '2023-03-14 22:24:48.645', 310, '2023-03-14 22:24:48.645', 310, True, False, False, '2022-01-03');

ALTER TABLE attendance_records
ADD CONSTRAINT unique_attendance_record 
UNIQUE  (employee_id, leave_request_id,in_time);

CREATE UNIQUE INDEX mytable_col1_col2_idx ON attendance_records (employee_id, leave_request_id,in_time)
  WHERE employee_id IS NOT NULL AND leave_request_id IS NOT NULL AND in_time IS NOT NULL;
  
  
ALTER TABLE attendance_records
ADD CONSTRAINT unique_attendance_record 
UNIQUE (employee_id, leave_request_id);