
feb month
feb differences
![[Pasted image 20230526142052.png]]

April
himanshu.kudal@metacube.com  3 row
![[Pasted image 20230526143628.png]]

dolly.sharma@metacube.com 21 row
![[Pasted image 20230526143753.png]]

wrs5@metacube.com
![[Pasted image 20230526143918.png]]

May
![[Pasted image 20230526144536.png]]
	![[Pasted image 20230526144605.png]]

![[Pasted image 20230526144652.png]]

sourabh.tejwani+22@metacube.com (ajay qa 10)
leave balance -2 new user
![[Pasted image 20230526144735.png]]
![[Pasted image 20230526144829.png]]

June
![[Pasted image 20230526145522.png]]

himanshu.kudal@metacube.com  3 row
![[Pasted image 20230526145610.png]]

![[Pasted image 20230526145656.png]]

july
![[Pasted image 20230526150305.png]]
![[Pasted image 20230526150414.png]]
![[Pasted image 20230526150504.png]]

aug
![[Pasted image 20230526151316.png]]
![[Pasted image 20230526151421.png]]

sept
![[Pasted image 20230526152221.png]]
![[Pasted image 20230526152310.png]]
![[Pasted image 20230526152444.png]]

oct
![[Pasted image 20230526153556.png]]
![[Pasted image 20230526153638.png]]
![[Pasted image 20230526153730.png]]
![[Pasted image 20230526153744.png]]

Nov
![[Pasted image 20230526155141.png]]

sourabh.tejwani+46@metacube.com  (new user 18 row)
![[Pasted image 20230526155256.png]]

![[Pasted image 20230526155353.png]]

Dec
wrs1+2@metacube.com (new user 54 row)
![[Pasted image 20230526160231.png]]
![[Pasted image 20230526160327.png]]
![[Pasted image 20230526160400.png]]
![[Pasted image 20230526160422.png]]

for(List<LeaveRequestDto> leaveRequestDtoList : consecutiveLeavesOfEmployee.subList(0,consecutiveLeavesOfEmployee.size()-1)){  
for(LeaveRequestDto leaveRequestDto : leaveRequestDtoList){  
leaveRequestDto.setTreatAsConsecutive(false);  
leaveRequestDto.setExtendsToNextMonth(false);  
updatedLeaveRequest.add(leaveRequestDto.toLeaveRecord());  
}  
}


if(!lastLeaveRequest.getFromDate().isAfter(lastWorkingDay) && lastLeaveRequest.getToDate().equals(lastWorkingDay)){  
for(LeaveRequestDto lr: lastLeaveRequestDtosList ){  
// if(lr.getToDate().isBefore(lastWorkingDay) ){  
// lr.setTreatAsConsecutive(false);  
// }  
// else  
lr.setTreatAsConsecutive(true);  
lr.setExtendsToNextMonth(false);  
updatedLeaveRequest.add(lr.toLeaveRecord());  
}  
}  
else if(!lastLeaveRequest.getFromDate().isAfter(lastWorkingDay) && lastLeaveRequest.getToDate().isAfter(lastWorkingDay)){  
for(LeaveRequestDto lr : lastLeaveRequestDtosList){  
lr.setExtendsToNextMonth(true);  
lr.setTreatAsConsecutive(false);  
updatedLeaveRequest.add(lr.toLeaveRecord());  
}  
}








may closure
sourabh.tejwani+22@metacube.com  (e_id > 7652) (ajay qa 10)  (leave balance -2)
master data is right
but adjsutment is wrong  (due to treat as consecutive)


after nov closure
sourabh.tejwani+46@metacube.com  (e_id > 7761)  (new user 18 row) (leave balance -1.5)
master data is right
but adjsutment is wrong  (due to treat as consecutive)

after dec closure 
wrs1+2@metacube.com   (e_id > 7743)(new user 54 row)  (leave balance -1)
master data is not right





Data was different when we are exporting annual leave summary (data was different on UI and sheet)
public Page<LeaveEntitlementSummaryDto> getEmployeesLeaveEntitlementById(LeaveEntitlementSummaryFilterDto leaveEntitlementSummaryFilterDto, List<Long> employeesIdList) throws AppException {  
  
return leaveEntitlementSummaryUtil.getEmployeesLeaveEntitlementById(leaveEntitlementSummaryFilterDto,null);


