## MARCH 2023 - SEPTEMBER 2023  

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


## OCTOBER 2023 - DECEMBER 2023 

1.  Worked on Attendance Closure Reminder Job which we send before monthly attendance closure
	1. Separate Auto closure Job and Auto closure  reminder Job.  
	2. Create a separate section to set auto attendance closure reminder job
	3. provide the functionality to select multiple days so worked on quartz to schedule a job with multiple trigger
	5. Challenges :- How to dynamically set the trigger for last,second last, third last day of month with trigger.
	6. Provide a option to rerun the attendance closure reminder job
	7. So learn that how can we set up multiple trigger with on quartz job.
2. Worked on  Reports:- 
	1. Replaced admin reports(Attendance Report,Attendance Summary) with Monthly Attendance report and summarised attendance report.
	2. Create a non periodic leaves report in admin report.
	3. Add Indicators and Comments for Pending Plans in Monthly Attendance Report
	4. Fix counts of Extra presence and Weekend Holidays on Attendance Summary.
	5. Fix some bugs related to columns (showing "N/A" values for manager and skip manager ) and attendance records should be visible after date of joining and till to date of joining.
	6. Pending Travel Plan issue in Monthly Attendance and attendance Summary showing "-" in monthly attendance UI (production issue).
	7.  Worked on Schedule Summarised Attendance Report .
		1. While selecting last month of year and duration as Quarterly and Yearly
3. Resolved validation  in the validation of leave type configuration.
4. Inspect the adjustment of Non-Periodic Leave(if employee apply leave for more than entitled days)
5. Worked on Manual Leave Adjustment
	1.  I contributed to the implementation of the Manual Leave Adjustment feature, enabling the manual correction of employee leave balances.
	2. I gained a comprehensive understanding of the monthly closure process and successfully integrated the manual leave adjustment into this process.
	3. As suggested we add more columns on monthly leave adjustment history like "isAdjusted".
	4. Make 'Leave Correction Count' Column Clickable, Opening a Pop-up with Adjustment Log Details on Click
6. Worked on Carry Forward
	1. Deactivate the current Entitlement Reset Schedule Job.
	2. Create an Entitlement Reset Job to Reset Active Entitlement  
	3. Create an Carry-Forward Job to Create Carry-Forward Leave
	4. Create an Carry-Forward Job to Auto Update Carry-Forward Leaves.
	5. Implement a confirmation pop-up for Update Carry Forward.
	6. Write SQL Queries to verify Carry-Forward flow at DB level.
	7. Fix Carry Forward Validation issues.
	8. Applied validation issue on carry forward form so user can't enter wrong value.
	9. UI Changes related to filter of Carry Forward and leave entitlement.
	10. This functionality allows us to provide employees with a carry forward form, enabling them to specify the number of leaves they wish to carry forward and the number they want to encash at the end of the accounting year.
7. Worked on Removing Mutual Exclusion
	1.  I undertook a code refactoring initiative in the current attendance record system.
	2. **Migrations :-** 
		1. Write migration to remove mutual exclusion
		2. Write a migration of Comp-Offs in order to set leave_request_id in Comp-Offs Records
		3. Write migrations of lwp attendance records.
	3.  Remove setting from Generic Configuration to mark unpaid extended leaves.
	4. Replace "Mark attendance for unpaid extended leave as " setting from attendance type to string
	5. We addressed a technical constraint that previously hindered the retrieval of information solely from attendance records. By removing this constraint and aligning the system.
8. Email Template :- 
	1. Email Notification for exhausted AUR limit sent to the Employees even after configuring "Maximum AUR allowed for missed or incorrect attendance"=0 in AMS Settings.


## JANUARY 2024 - MARCH 2024 
1. Worked on Carry Forward
	1. Implement a confirmation pop-up for Update Carry Forward.
	2. Worked on Annual Leave History UI to fetch les related data with carry forward.
	3. Apply sorting on leave summary UI.
	4. Fix Carry Forward UI issues impacted on various UIs.
	5. Change Scheduling in Carry Forward Auto Submission Job
	6. Check Duplicate Carry Forward Issue
	7. My/Employee-Leave Balance| Leave Summary| Remove the Action icon for "Fill your Leave Encashment Choices" from the Employees with Zero and Negative Leave Balance 
	8.  Update leave balance and prev_remaining_days in les
2. . Worked on Removing Mutual Exclusion
	1.  Use leave_request_id from attendance_records instead of adjust_against_leave_request_id of comp_off.
	2. Handle Unpaid leaves and CompOff Availed in Attendance Summary Report.
	3. # Update Field References in GenericConfiguration and CompensatoryOff Entities
3. Create a Documentation  regarding Carry-Forward reopen after update
4. Worked on Attendance summary report
	1. Employee's/Team's Attendance Summary| "Weekend/Holiday" count includes the Leaves marked on weekend/holiday dates
	2. Add CompOff related column on Employee/Team Attendance-View Attendance| Attendance Summary|  (- Comp Off Available,Comp Off Availed,Comp Off Expired)
	3. Some columns sum up to a month(attendance closure period). Paid Leave (including leaves marked as unpaid or adjusted against comp off)
	4. - Some extra columns like LWP, Comp offs availed will be there. We should show such columns in the right most corner of the table.
	5. - We can also add some background color to distinguish the above two column sets.
	6. Test Attendance Summary and CompOff Summary (Admin Reports) on Beta Environment.
	7. # In Monthly Attendance Report ,Add "In Time" and "Out Time" columns with respect to each day of month
5. Admin Reports :- 
	1. Admin Report | Create a Comp Off Summary Report  
6. Manual Leave Adjustment :- 
	1. "Enhance User Interface: Make 'Leave Correction Count' Column Clickable, Opening a Pop-up with Adjustment Log Details on Click
	2. Employee/Team/My Attendance-Leave Balance| Monthly Leave Adjustment History| Rename the column header "Effective Date" as "Effective Period for adjustment".
	3.  My Attendance, Employee Attendance | Leave Balance | Leave Summary Reorder Columns in Leave Summary UI.
	4.  My Attendance, Employee Attendance | Leave Balance | Leave Summary Add "Leave Correction Count" column to Leave Summary UI with Clickable Pop-up
	5.  Beta| Leave Balance| Create a query to display all the records of Leave Corrections made by System on Monthly Leave Adjustment History UI
7. Non Periodic Leave Validation :- 
	1. Employee Attendance-Leave Balance| Monthly Leave Adjustment History| Something went wrong error.
	2. # Verify the correct functioning of the method isLeaveAppliedForMoreThanMaximumDays for Paternity Leave
	3.  Newly Onboarded Employees are able to apply Leave, AUR and EH before their joining date.
	4. Apply a validation check to ensure that plans, specifically Leave, AUR, and EH, are not assigned to an employee before their joining date.
	5. Weekend and Holiday should marked for Non periodic leave.
	6. # My/Reportee's/Employee's Leave Plans| Updating the "Reason" input field in an already existing Leave Plan displays "Basic Leave Validation Failed" Message
8. # Explore Generic Configuration of Non-Periodic Leaves: Total Entitlement Days and Gap Between Instances (Documentation)
9. # Exported Sheets| Date and Number should not be displayed in Text/String format
10. Start to explore shift and TimeZones.
11. Employee Attendance-Leave Balance| Monthly Leave Adjustment History| Something went wrong error

Communication Breakdowns: In some cases, there were breakdowns in communication between team members, leading to misunderstandings or duplication of efforts. Change Management: Managing changes to project requirements or timelines proved to be a challenge at times, particularly when changes were requested late in the project lifecycle. This disrupted workflow and required adjustments to accommodate new requirements.


Impact and Achievements:
Improved Reporting Functionality: The enhancements made to the Attendance Summary and Monthly Attendance Reports have significantly improved reporting capabilities, providing users with more comprehensive and customizable reporting options.

Enhanced User Experience: The improvements to manual leave adjustment and implementation of non-periodic leave validation have contributed to a smoother and more efficient user experience, reducing the likelihood of errors and improving overall usability.

Technical Debt Reduction: Addressing mutual exclusion issues and resolving technical debts have improved the overall stability and maintainability of the system, paving the way for future development and enhancements.
Documentation of Carry-Forward Reopen Process:

Designed comprehensive documentation outlining the process of carrying forward and reopening leave balances .



Prioritized understanding all requirements thoroughly through in-depth discussions with stakeholders. Ensured clarity and alignment of objectives before proceeding with implementation.
Prepared comprehensive documentation for new features from the user's point of view. Documented user stories, use cases, and workflows to provide clear guidance and facilitate seamless adoption of new functionalities.

## APRIL 2024 - JUNE 2024
1. Start to working on Shift and Time-Zone functionality.
	1. Start to gathering requirement what all major changes we need to do in application to incorporate multiple shift.
	2. Have multiple technical design discussions on shift functionality.
	3. Start to prepare test cases to handle different shifts of different timezone (Calculate shift start time, end time, shift time, au time)
	4. Prepare scenarios of DST (Day light saving time).
	5. Explore java apis (java.time) to handle date, time and timezone in efficient ways.
	6. Start to preparing technical documents of existing functionality. (Daily Attendance Closure Job, Monthly Attendance closure)
	7. Start to working on CRUD operations of AU and Shift.
	8. After completion of CRUD operations start to prepare documentation of schedule plans. Added test case to incorporate au and shift in group hierarchy. Explore the code base of schedule plans.
	9. Start to work on adding AU and Shift schedule plans.
		1. Changes in existing functionality.
		2. Associate AU and Shift in to get current inherited plan of a given group id, list tree view
		3. Stuck in special cases for associating AU and shift with schedule plans. Discussed with KS and perform changes .
		4. Apply validation for creation of schedule plans.
		5. Apply validation for the creation of first group schedule plans.While creating first group schedule plan all fields should be mandatory to be filled.
	10. After completion of schedule plans association, start to working on changes impacted of daily attendance closure date on plans and etc.. functionality.
	11. Enhancement related to multiple shift 
		1. Identify and list down the signature of all required util methods of shift timing.
		2.  Added util methods required for shift-related date and time operations.
		3. Created entity, dto, controller and service of attendance closure log table. Perform CRUD operation of closure logs.
		4. Conned with ﻿Shobhit Dixit﻿ to discuss the changes required of shift in wrs user and employee details .
		5. Prepare Recursive SQL query to fetch shift and AU from group hierarchic.
		6. Create abstract method to get AU and Shift from wrs user.
		7. Create abstract/util methods required in plans to fetch AU and Shift.
		8. Create some unit methods to identify that is plans is of past, future and on-going.
		9. Implement changes related to AU and Shift in plan validations.
		10. Added shift instance dto in plan validator context and used it’s properties in plan validations.
		11. Remove all usages of LocalDate.now() (Current Date), LocalDateTime.now() (Current Date Time), and LocalTime.now() (Current Time) from plan validations.
	12. List down all the changes in punch in/out related APIs to support multiple shift.
	13. List down all usages of shifts in various plans.List down the description of all usages, how can we refactor these functions.
	14. List down the required changes on UI regarding shift at broad level.
	15. List down all the usages of LocalDate.now() in validator and plans related files.
2. Update changes of mutual exclusion (after completion of comp-off tasks), After deployment provide support to QA and fix bug reported by QA. 
	1. (Apply indexing on attendance records)
	2. Inspect issue arise due to importing of attendance records. 
	3. Perform testing of whole application on beta.
	4. Remove commented code with this Epic.
3. Fix production issue EH attendance records is not creating if approved after daily attendance closure.
4. Fix issue of creating multiple EH plan creation. (Disabled submit button to avoid multiple creation of EH on same day.) 
5. Fix issue in daily attendance report (regarding in time, regarding number of cell style can be up to a limit). 
6. Added a note of elective holiday in closure summery report.
7. Inspect wrs api “get Group Hierarchy Tree For Org“ is not working with app token.Add a parameter of organisation id .
8. Explore validation of daily attendance closure on leave plans and EH plans actions during execution.
9. Prevent execution of Daily Attendance Closure Job for future dates.
10. Prepare a documentation for enhancement of admin reports as discussed. List down challenges and update as per KS feedback.

