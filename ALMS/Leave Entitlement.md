
@Column(name = "employee_id",nullable = false)  
private Long employeeId;  
  
@Column(name = "employee_uuid",nullable = false)  
private UUID employeeUUId;  
  
@Column(name = "organization_id",nullable = false)  
private Long organizationId;  
  
@Column(name = "organization_uuid",nullable = false)  
private UUID organizationUUId;  
  
@Column(name = "start_date",nullable = true)  
private LocalDate startDate;  
  
@Column(name = "end_date",nullable = true)  
private LocalDate endDate;  
  
@Builder.Default  
@Column(name = "total_leave_taken",nullable = false)  
private Long leavesTaken = 0l;  
  
@Column(name = "total_accrued_leave",nullable = false)  
private Float totalAccruedLeave;  
  
@Column(name = "total_non_accrued_leave",nullable = false)  
private Float totalNonAccruedLeave;  
  
@JsonManagedReference  
@OneToOne  
@JoinColumn(name= "carry_forward_leave_id", referencedColumnName = "id")  
private CarryForwardLeave carryForwardLeave;  
  
@Column(name = "leave_instance_id")  
private Long leaveInstanceId;  
  
// @Column(name="max_leave_allowed")  
// private Float maxLeavesAllowed;  
  
@Builder.Default  
@Column(name = "status")  
@Enumerated(EnumType.STRING)  
@Type(type = "com.metacube.tms.entity.SQLEnumType")  
private ActiveStatusEnum status = ActiveStatusEnum.ACTIVE;  
  
@Column(name = "last_accrual_date",nullable = true)  
private LocalDate lastAccrualDate;  
  
@OneToOne  
@JoinColumn(name= "generic_configuration_id", referencedColumnName = "id")  
private GenericConfiguration genericConfiguration;  
  
@Column(name = "opening_balance")  
private Float openingBalance;  
  
@OneToMany(mappedBy = "leaveEntitlementSummary", fetch = FetchType.LAZY, cascade = CascadeType.ALL)  
private List<MonthlyLeaveSummaryAccountEntity> monthlyLeaveSummaries = new ArrayList<>();



fields of DTO
// Leave type  
private LeaveType leaveType;  
  
// Total leave accrued or non accrued + last year carry forward available on current date  
private Float totalLeave;  

// Total leave taken  
private Long leavesTaken;  

// Adjusted total leave pending  
private Long leavesPending;  

// Real total leave pending  
private Float realLeavesPending;  

// Total leave available ( including total leave accrued and last year carry  
// forward)  
@Builder.Default  
private Float openingBalance = 0f;  

// Total leave scheduled in future  
private Long leavesScheduled;  

// Total leave accrued  
private Float totalAccruedLeave;  
// Total leave non accrued  
private Long totalNonAccruedLeave;  
  
// private Long maxCarryForward;  
// private Long maxEncash;  
private Long toBeCarryForward;  
private Long toBeEncash;  
  
private LocalDate joiningDate;  
  
private CarryForwardLeaveDto carryForwardFromLastYear;  
private Boolean isLeaveExpired;  
  
private Long totalLWP;  
private Long totalTakenLWP;  
private Long totalScheduledLWP;  
//private Float maxLeavesAllowed;  
  
private ActiveStatusEnum status;  
  
private Long adjustedCompOffCount;  
  
  
  
@Builder.Default  
private boolean isCarryForwardAvailable = false;  
  
private Long employeeId;  
private String employeeEmail;  
private String employeeFullName;  
private UUID employeeUUID;  
private String employeeImageUrl;  
private Long organizationId;  
private UUID organizationUUId;  
private Long leaveInstanceId;  
  
private LocalDate startDate;  
private LocalDate endDate;  
  
private String otherAttendanceString;  
private LocalDate lastAccrualDate;  
  
private GenericConfigurationDto genericConfiguration;  
public List<DurationBasedConfigurationDto> durationBasedConfigurationDtoList;  
  
public Float leaveBalance;



mainly three fields which we have in leave entitlement
	private Long leavesTaken = 0l;   
	private Float totalAccruedLeave;
	private Float openingBalance;

getEmployeesLeaveEntitlementById  method in leave lentitlementSummaryutil class is used for 
conversion of leaveEntitlementSummary to leaveEntitlementSummaryDTO
	1. Page<LeaveEntitlementSummary> pageEmployeeEntitlement  list of leave entitlement summary
	2. first we convert  les to lesDTO
		1. List<LeaveEntitlementSummaryDto> employeeEntitlements
	3. List<Long> foundEntitlementEmployeeIds =
	4. Map<Long,Long> employeeLeaveScheduledMap = count of all leaves for a particular leave type of each employee
	5. Map<Long,Long> employeeIdAndLeaveInstanceCountMap = count of all leaves instance for a particular leave type  of each employee
		1. Map<Long,Long> employeeAdjustedCompOffCount = count of all adjusted compOff count  for a particular leave type of each employee


public void createEntitlementsFor(List<EmployeeDetailsDto> employeeList,List<Long> entitlementIds, LocalDate startDate){
 method in leave-entitlementServiceImpl class used to create entitlement 