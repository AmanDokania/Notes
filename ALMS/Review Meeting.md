1. User should not be able to punch-in via Mobile Device
	1. After that there were some enhancement that Selective users those use VPN should allow to punch-in via mobile
	2. WRS sync job > Earlier complete wrs table refreshed in ALMS  but now some selective properties will be sync 

2. Worked with Akshay on leave adjustment 
	1. get an idea about flow and logic of leave adjustment
	2. perform DEV testing while running closure to test the report 
	3. Help to Participate in small tasks of leave adjustment
		1. Provide setting accrual will be after or before monthly closure 
		2. What are all the attendance and leave type we can exclude form accrual

3. After that worked on Adjustment of Non-Periodic Leave
4. We should be able to assign a entitlement of Non-Periodic Leave
5. Worked on Garden Leave (Special Leave ) which can only be assign by admin or can't be cancel by employee
6. Worked on leave and plan validation 
	1. We should not be able to applied leave for more than entitled days
	2. Holidays and weekends are not marking while applying for non-periodic leaves
	3. we were able to create multiple plans for a single day
	
7. Worked on email template (Some of the template need to write form scratch and some of them need to edit)
8. Worked on Admin Reports
9. Worked on My-attendance page (Review the code how we show overlapped plans and pending plans is visible with pending sign and tooltip )
	1. There were some mismatch in statics of attendance summary page (extra presence count)
	2. As we display pending plans with sign and tooltip exported sheet should also contain the same

10. Worked on Attendance Closure Reminder Job which we send before monthly attendance closure
	1. Create a separate section to set auto attendance closure reminder job
	2. provide the functionality to select multiple days so worked on quartz to schedule a job with multiple trigger