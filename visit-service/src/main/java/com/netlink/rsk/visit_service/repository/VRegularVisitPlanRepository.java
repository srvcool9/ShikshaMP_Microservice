package com.netlink.rsk.visit_service.repository;


import com.netlink.rsk.visit_service.model.RoleMaster;
import com.netlink.rsk.visit_service.model.VregularVisitPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Repository
public interface VRegularVisitPlanRepository extends JpaRepository<VregularVisitPlan,Long> , JpaSpecificationExecutor<VregularVisitPlan>{
	
	List<VregularVisitPlan> findAll();
    Optional<VregularVisitPlan> findByvregularvisitid(Long id);
    public VregularVisitPlan findByRole(RoleMaster roleMaster);

    List<VregularVisitPlan> findByMonthAndYear(Integer month, Integer year);

    boolean existsByRoleAndMonth(RoleMaster role, Integer month);

    boolean existsByRoleidAndMonthAndYear(int roleid, Integer month,Integer year);

    List<VregularVisitPlan> findByRoleOrderByCreatedonDesc(RoleMaster role);

    Optional<VregularVisitPlan> findByRoleid(int roleid);

    VregularVisitPlan findFirstByRoleidAndMonthAndYear(int roleid, Integer month,Integer year);

    VregularVisitPlan findFirstByRole_RoleIDAndMonthAndYear(Long roleID, Integer month, Integer year);

    @Transactional
    @Procedure("dbo.Up_AllRegularVisitByRoleAndMonth")
    List<Object[]> allRegularVistiStatusReeportByRoleIdAndMonth(@Param(value = "RoleId") Integer RoleId,
                                                              @Param(value = "MonthId") Integer MonthId);

    @Transactional
    @Procedure("dbo.Up_AllRegularVisitByRoleAndMonth")
    List<Object[]> allRegularVistiStatusReeportByRoleIdAndMonthForEmployee(@Param(value = "RoleId") Integer RoleId,
                                                                @Param(value = "MonthId") Integer MonthId,
                                                                           @Param(value = "EmployeeID") Integer EmployeeID);

    @Transactional
    @Procedure("dbo.Up_AllAreaRegularVisitByLevelID")
    List<Object[]> allRegularVisitByLevel(@Param(value = "Level") String  Level);

    @Transactional
    @Procedure("dbo.Up_AllAreaRegularVisitByLevelID")
    List<Object[]> allRegularVisitByLevel(@Param(value = "Level") String  Level, @Param(value = "LevelId") String  LevelId);

    @Transactional
    @Procedure("dbo.Up_AllAreaRegularVisitByLevelID")
    List<Object[]> allRegularVisitBySchoolId(@Param(value = "Level") String  Level, @Param(value = "LevelId") String  LevelId, @Param(value = "SchoolId") String  SchoolId);

    @Transactional
    @Procedure("dbo.Up_AllSchoolWiseParameterReportByLevelID")
    List<Object[]> allschoolwiseparameterreportbylevel(@Param(value = "Level") String  Level);




    @Transactional
    @Procedure("dbo.Up_AllSchoolWiseParameterReportBySchoolIDAndRoleID")
    List<Object[]> allSchoolWiseParameterReportBySchoolIDAndRoleID(@Param(value = "RoleId") Integer RoleId,
                                                                @Param(value = "SchoolId") String SchoolId);



    @Transactional
    @Procedure("dbo.Up_AllSpotAssesmentReportByLevelID")
    List<Object[]> allSpotAssesmentReportByLevel(@Param(value = "Level") String  Level);
    @Transactional
    @Procedure("dbo.Up_AllSpotAssesmentReportByLevelID")
    List<Object[]> allSpotAssesmentReportbylevelStartDateAndEndDate(@Param(value = "Level") String  Level,@Param(value = "StartDate") String  startDate,@Param(value = "EndDate") String  endDate);


    @Transactional
    @Procedure("dbo.Up_AllSpotAssesmentReportByLevelID")
    List<Object[]> AllSpotAssesmentReportbylevelStartDateAndEndDatelevelID(@Param(value = "Level") String  Level,@Param(value = "StartDate") String  startDate,@Param(value = "EndDate") String  endDate,@Param(value = "LevelId") String  levelId);

    @Transactional
    @Procedure("dbo.Up_AllSpotAssesmentReportByLevelID")
    List<Object[]> AllSpotAssesmentReportbylevelStartDateAndEndDatelevelIDWithClusterCode(@Param(value = "Level") String  Level,@Param(value = "StartDate") String  startDate,@Param(value = "EndDate") String  endDate,@Param(value = "LevelId") String  levelId,@Param(value = "ClusterId") String  ClusterId);

    @Transactional
    @Procedure("dbo.Up_AllSpotAssesmentReportByLevelID")
    List<Object[]> AllSpotAssesmentReportbylevelStartDateAndEndDatelevelIDWithClusterCodeSchoolCode(@Param(value = "Level") String  Level,@Param(value = "StartDate") String  startDate,@Param(value = "EndDate") String  endDate,@Param(value = "LevelId") String  levelId,@Param(value = "ClusterId") String  ClusterId,@Param(value = "SchoolId") String  SchoolId);



}
