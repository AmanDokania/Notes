monthly leave summary account
		 1. employee_id
		 2.closing_date


LeaveEntitlementSummaryController ("api")"/export/entitlement-summary-data"
leave entitle fileter DTO
	1. groupId
	2. managet type default>primary manager
	3. year


List<Long> employeeIds = null;  
if (CollectionUtils.isNotEmpty(leaveEntitlementSummaryFilterDto.getEmployeeIds())) {  
employeeIds = leaveEntitlementSummaryFilterDto.getEmployeeIds();  
} 
else if(isCurrentUserAdmin && Objects.isNull(leaveEntitlementSummaryFilterDto.getManagerType())){  
if(leaveEntitlementSummaryFilterDto.getGroupId() != null) {  
employeeIds = wrsService.getEmployeeDetailsByGroupId(Arrays.asList(leaveEntitlementSummaryFilterDto.getGroupId())).stream().map(EmployeeDetails::getUserID).collect(Collectors.toList());  
}  
} 
else{  
if(Objects.isNull(leaveEntitlementSummaryFilterDto.getManagerType())) {  
leaveEntitlementSummaryFilterDto.setManagerType(ManagerType.PRIMARY_MANAGER);  
}

what we have >>
leave type,
employee id, 
closing date

what we are taking >>
form date
to date
leave type id

