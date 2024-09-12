package com.netlink.rsk.visit_service.repository;


import com.netlink.rsk.visit_service.mapper.*;
import com.netlink.rsk.visit_service.mapper.DashboardReports.UniqueSchoolsReportMapper;
import com.netlink.rsk.visit_service.model.VisitUserSchoolSelfAllocationXref;
import com.netlink.rsk.visit_service.queries.VisitQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Repository
public interface VisitUserSchoolSelfAllocationXrefRepository  extends JpaRepository<VisitUserSchoolSelfAllocationXref,Long> , JpaSpecificationExecutor<VisitUserSchoolSelfAllocationXref>{
	List<VisitUserSchoolSelfAllocationXref> findByRoleidAndMonthAndYear(int roleid,Integer month,Integer year);

	public List<VisitUserSchoolSelfAllocationXref> findByemployeecode(String empCode);

	List<VisitUserSchoolSelfAllocationXref> findByEmployeecodeAndIsdeleted(String employeecode, Boolean isdeleted);

	@Query(value = VisitQueries.FIND_BY_EMPLOYEECODE_And_ROLEID_AND_MONTH_AND_ISDELETED_ORDERBY_SCHOOLALLOCATIONIDDESC,nativeQuery = true)
	List<VisitUserSchoolSelfAllocationXref> findByEmployeecodeAndRoleidAndMonthAndIsdeletedOrderBySchoolallocationidDesc(String employeecode, int roleid,int month, int year ,Boolean isdeleted);


	@Query(value = VisitQueries.GET_START_VISIT_LIST,nativeQuery = true)
	public List<XrefMapper> getStartVisitList(@Param("EmployeeCode") String empCode,
											  @Param("RoleId") Long roleId,
											  @Param("Month")Integer month,
											  @Param("Year") Integer year,
											  @Param("IsDeleted")Boolean isDeleted);


	@Query(value = VisitQueries.GET_DATE_STATUS,nativeQuery = true)
	public String getDateStatus(@Param("DateToCheck")Date date);

	@Query(value = VisitQueries.FIND_BYEMPLOYEECODE_ALLOCATED_TODAY_ORDERBY_ALLOCATEDDATEDESC,nativeQuery = true)
	List<VisitUserSchoolSelfAllocationXref> findByEmployeecodeAllocatedTodayOrderByAllocateddateDesc(String employeecode, int roleid);

	long countByRoleidAndStatus(int roleid, String status);

	long countByEmployeecodeAndRoleidAndMonthAndYearAndStatusIgnoreCase(String employeecode, int roleid, int month, int year, String status);

	long countByEmployeecodeAndMonthAndYearAndStatusAllIgnoreCase(String employeecode, int month, int year, String status);

	long countByEmployeecodeAndRoleidAndStatus(String employeecode, int roleid, String status);

	@Transactional
	@Modifying
	@Query(VisitQueries.UPDATE_STATUS_BY_SCHOOLALLOCATIONID)
	int updateStatusBySchoolallocationid(@Param("status") String status,@Param("allocationId") Long schoolallocationid);



	@Query(value = VisitQueries.GET_AVERAGE_TIME, nativeQuery = true)
	String getAverageTime(@Param("employeecode") String employeecode,@Param("roleId") int roleId,@Param("month") int month,@Param("year") int year);


	@Query(value = VisitQueries.GET_CURRENT_MONTH,nativeQuery = true)
	public Integer getCurrentMonth();

	@Query(value = VisitQueries.GET_CURRENT_YEAR,nativeQuery = true)
	public Integer getCurrentYear();

	@Query(value = VisitQueries.GET_CURRENT_TIME,nativeQuery = true)
	public Date getCurrentTime();

	@Query(value = VisitQueries.GET_CURRENT_DATE,nativeQuery = true)
	public Date getCurrentDate();


	@Query(value = VisitQueries.GET_VISIT_YEAR,nativeQuery = true)
	 public List<Integer> getVisitYear();
	long countByRoleidInAndEmployeecodeAndMonthAndYearAndStatus(Collection<Integer> roleids, String employeecode, int month, int year, String status);

	@Query(value = VisitQueries.GET_UNIQUE_SCHOOL_VISITS_FOR_BLOCK,nativeQuery = true)
	public Long getUniqueSchoolVisitsForBlock(@Param("BlockId")String blockId,
											  @Param("Month")Integer month,
											  @Param("Year")Integer year);

	@Query(value = VisitQueries.GET_CLUSTER_UNIQUE_SCHOOL_VISIT_BY_BLOCK,nativeQuery = true)
	public List<StateLMapper> getClusterUniqueSchoolVisitByBlock(@Param("BlockId")String blockId,
																 @Param("Month")Integer month,
																 @Param("Year")Integer year);

	@Query(value = VisitQueries.GET_VISIT_HOURS,nativeQuery = true)
	public Integer getVisitHours(@Param("SchoolAllocationId") long schAllocationId);

	@Query(value = VisitQueries.GET_UPDATE_VISIT_TIME,nativeQuery = true)
	@Modifying
	@Transactional
	public int updateVisitTime(@Param("SchoolAllocId")Long schoolAllocationId);

	@Transactional
	@Modifying
	@Query(VisitQueries.UPDATE_STATUS_AND_START_TIME_AND_ENDTIME_BY_SCHOOLALLOCATIONID)
	int updateStatusAndStarttimeAndEndtimeBySchoolallocationid(String status, Date starttime, Date endtime, Long schoolallocationid);


	@Query(value =VisitQueries.GET_TOTAL_VISITS,nativeQuery = true)
	public Long getTotalVisits(@Param("Month") Integer month,
							   @Param("Year")Integer year);

	@Query(value = VisitQueries.GET_VISIT_PERCENT_TAKING_MORE_TIME,nativeQuery = true)
	public Long getVisitPercentTakingMoreTime(@Param("Month") Integer month,
											  @Param("Year")Integer year);

	@Query(value = VisitQueries.GET_VISIT_AVERAGE_TIME,nativeQuery = true)
	public double getVisitAverageTime(@Param("Month") Integer month,
									  @Param("Year")Integer year);

	@Query(value = VisitQueries.GET_UNIQUE_SCHOOLS_COUNT,nativeQuery = true)
	public Long getUniqueSchoolsCount(@Param("Month") Integer month,
									  @Param("Year")Integer year);

	@Query(value = VisitQueries.GET_UNIQUE_SCHOOL_STATE_LEVELREPORT,nativeQuery = true)
	public List<UniqueSchoolsReportMapper> UniqueSchoolStateLevelReport(@Param("Month") Integer month,
																		@Param("Year")Integer year);

	@Query(value = VisitQueries.GET_UNIQUE_SCHOOL_DISTRICT_LEVEL_REPORT,nativeQuery = true)
	public List<UniqueSchoolsReportMapper> UniqueSchoolDistrictLevelReport(@Param("Month") Integer month,
												@Param("Year")Integer year,
																		   @Param("AreaId")String areaId);

	@Query(value = VisitQueries.GET_UNIQUE_SCHOOL_BLOCK_LEVEL_REPORT,nativeQuery = true)
	public List<UniqueSchoolsReportMapper> UniqueSchoolBlockLevelReport(@Param("Month") Integer month,
																		@Param("Year")Integer year,
																		@Param("AreaId")String areaId);

	@Query(value = VisitQueries.GET_VISITS_FOR_MENTOR,nativeQuery = true)
	public List<UserSelfSchoolAllocationXrefMapper> getMyVisitForMentor(@Param("EmployeeCode")String mentorId);

	@Query(value = VisitQueries.GET_VISITS_FOR_UDISE_CODE,nativeQuery = true)
	public List<UserSelfSchoolAllocationXrefMapper> getMyVisitForSchool(@Param("UdiseCode")String udiseCode);


	@Query(value = VisitQueries.GET_VISIT_DETAILS,nativeQuery = true)
	public VisitDetailMapper getVisitDetails(@Param("SchoolAllocationId")Long schoolAllocationId);

	@Query(value = VisitQueries.GET_MY_ALLOCATED_VISITS,nativeQuery = true)
	public List<MyAllocatedVisitsMapper> getMyAllocatedVisits(@Param("EmpCode") String employeeCode,
															  @Param("RoleId") Long roleId,
															  @Param ("Month") Integer month,
															  @Param("Year") Integer year);
}
