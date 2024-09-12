package com.netlink.rsk.visit_service.repository;

import com.netlink.rsk.visit_service.mapper.SchoolInfoClusterBlockMapper;
import com.netlink.rsk.visit_service.mapper.SchoolInfoDistrictMapper;
import com.netlink.rsk.visit_service.mapper.StudentAttendanceReportMapper;
import com.netlink.rsk.visit_service.model.SchoolInformation;
import com.netlink.rsk.visit_service.queries.VisitQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface SchoolInformationRepository extends JpaRepository<SchoolInformation, Long>, JpaSpecificationExecutor<SchoolInformation> {

    SchoolInformation findByVisitUserSchoolSelfAllocationXref_Schoolallocationid(Long schoolallocationid);

    @Query(value = VisitQueries.GET_ALL_SCHOOLINFORMATIONID_BY_SCHOOLALLOCATIONID, nativeQuery = true)
    Long findBySchoolallocationid(Long schoolallocationid);

    long deleteByVisitUserSchoolSelfAllocationXref_Schoolallocationid(Long schoolallocationid);

    boolean existsByVisitUserSchoolSelfAllocationXref_Schoolallocationid(Long schoolallocationid);

    @Query(value = VisitQueries.GET_ALL_STATE_REPORT,nativeQuery = true)
    public Long getStateReport(@Param("ClassIds") List<Long> classIds,
                               @Param("Month")Integer month,
                               @Param("Year")Integer year);

    @Query(value = VisitQueries.GET_ALL_District_REPORT,nativeQuery = true)
    public List<SchoolInfoDistrictMapper> getAllDistrictReport(@Param("ClassIds") List<Long> classIds,
                                                               @Param("Month")Integer month,
                                                               @Param("Year")Integer year);

    @Query(value = VisitQueries.GET_ALL_SCHOOL_TIME_TABLE_REPORT_FOR_BLOCK,nativeQuery = true)
    public Long getSchoolTimeTableReportForBlock(@Param("BlockId")String blockId,
                                                 @Param("ClassIds") List<Long> classIds,
                                                 @Param("Month")Integer month,
                                                 @Param("Year")Integer year);

    @Query(value = VisitQueries.GET_ALL_CLUSTER_REPORT_BY_BLOCK,nativeQuery = true)
    public List<SchoolInfoClusterBlockMapper> getClusterReportByBlock(@Param("BlockId")String blockId,
                                                                      @Param("ClassIds") List<Long> classIds,
                                                                      @Param("Month")Integer month,
                                                                      @Param("Year")Integer year);

    @Query(value = VisitQueries.GET_DISTRICT_LEVEL_STUDENT_ATTENDANCE_REPORT,nativeQuery = true)
    public List<StudentAttendanceReportMapper> getDistrictLevelStudentAttendanceReport(@Param("ClassId")Long classId,
                                                                                       @Param("Year")Long year,
                                                                                       @Param("Month")Long month);

    @Query(value = VisitQueries.GET_BLOCK_LEVEL_STUDENT_ATTENDANCE_REPORT,nativeQuery = true)
    public List<StudentAttendanceReportMapper> getBlockLevelStudentAttendanceReport(@Param("ClassId")Long classId,
                                                                                    @Param("Year")Long year,
                                                                                    @Param("Month")Long month,
                                                                                    @Param("AreaId") String areaId);

    @Query(value = VisitQueries.GET_CLUSTER_LEVEL_STUDENT_ATTENDANCE_REPORT,nativeQuery = true)
    public List<StudentAttendanceReportMapper> getClusterLevelStudentAttendanceReport(@Param("ClassId")Long classId,
                                                                                      @Param("Year")Long year,
                                                                                      @Param("Month")Long month,
                                                                                      @Param("AreaId") String areaId);
}