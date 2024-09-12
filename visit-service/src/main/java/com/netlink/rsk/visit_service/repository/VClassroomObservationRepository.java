package com.netlink.rsk.visit_service.repository;

import com.netlink.rsk.visit_service.mapper.DashboardReports.*;
import com.netlink.rsk.visit_service.mapper.MentoringReportMapper;
import com.netlink.rsk.visit_service.mapper.SchoolInfoBlockDistrictMapper;
import com.netlink.rsk.visit_service.mapper.SchoolInfoDistrictMapper;
import com.netlink.rsk.visit_service.model.VClassroomObservation;
import com.netlink.rsk.visit_service.queries.VisitQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VClassroomObservationRepository extends JpaRepository<VClassroomObservation, Long> {
    VClassroomObservation findFirstBySchoolallocationid(int schoolallocationid);
    List<VClassroomObservation> findBySchoolallocationidAndSubsectionid(int schoolallocationid, int subsectionid);
    VClassroomObservation findBySchoolallocationid(int schoolallocationid);
    boolean existsBySchoolallocationid(int schoolallocationid);

    boolean existsBySchoolallocationidAndSubsectionid(int schoolallocationid, int subsectionid);

    VClassroomObservation findFirstBySchoolallocationidOrderByClassroomobservationidDesc(int schoolallocationid);

    long deleteBySchoolallocationid(int schoolallocationid);

    @Query(value = VisitQueries.GET_ALL_DISTRICT_WISE_QUESTION_RESP_PER,nativeQuery = true)
    public SchoolInfoDistrictMapper getDistrictWiseQuestionRespPer(@Param("ClassIds") List<Long> classIds,
                                                                   @Param("DistrictId")String districtId,
                                                                   @Param("Year")Integer year,
                                                                   @Param("Month") Integer month);

    @Query(value = VisitQueries.GET_BLOCK_WISE_QUES_RESP_PER,nativeQuery = true)
    public List<SchoolInfoBlockDistrictMapper> getBlockWiseQuesRespPer(@Param("ClassIds") List<Long> classIds,
                                                                       @Param("DistrictId")String districtId,
                                                                       @Param("Year")Integer year,
                                                                       @Param("Month")Integer month);

    @Query(value = VisitQueries.GET_MENTORING_REPORT,nativeQuery = true)
    public List<MentoringReportMapper> getMentoringReport(@Param("SchoolAllocationId")Long schoolAllocationId);














    @Query(value = "EXEC Visit.rpt_MGMLClassRoom @DistrictCode = :districtCode, @BlockCode = :blockCode, @SectionId = :sectionId, @ClassId = :classId, @Year = :year, @Month = :month", nativeQuery = true)
    List<COParameterReportMapper> getPercentageOfMGMLClassRooms(@Param("districtCode") String districtCode,
                                                                @Param("blockCode") String blockCode,
                                                                @Param("sectionId") String sectionId,
                                                                @Param("year") String year,
                                                                @Param("month") String month,
                                                                @Param("classId") String classId);

    @Query(value = "EXEC Visit.rpt_ParameterPercentages  @DistrictCode=:districtCode,@BlockCode=:blockCode,@ParameterId=:parameterId ,@ClassId =:classId, @Year = :year,@Month = :month", nativeQuery = true)
    public List<MGMLParamMapper> getMGMLCOPracticesReport(
            @Param("districtCode") String districtCode,
            @Param("blockCode") String blockCode,
            @Param("parameterId") String parameterId,
            @Param("year") String year,
            @Param("month") String month,
            @Param("classId") String classId
    );


    @Query(value = "EXEC Visit.rpt_WBUsages  @DistrictCode = :districtCode, @BlockCode = :blockCode, @SectionId = :sectionId, @Year = :year, @Month = :month, @ClassId = :classId", nativeQuery = true)
    public List<COParameterReportMapper> getWBUsageAndFeedBackRateReport(
            @Param("districtCode") String districtCode,
            @Param("blockCode") String blockCode,
            @Param("sectionId") String sectionId,
            @Param("year") String year,
            @Param("month") String month,
            @Param("classId") String classId
    );



    @Query(value = "EXEC Visit.rpt_PPercentagesByPValueId  @DistrictCode = :districtCode, @BlockCode = :blockCode, @ParameterValueId = :parameterValueId, @Year = :year, @Month = :month", nativeQuery = true)
    public List<PPercentagesByPValueIdMapper> getPPercentagesByPValueIdReport(
            @Param("districtCode") String districtCode,
            @Param("blockCode") String blockCode,
            @Param("parameterValueId") String parameterValueId,
            @Param("year") String year,
            @Param("month") String month
    );


    @Query(value = "EXEC Visit.rpt_ClassRoomMaterial @DistrictCode=:districtCode,@BlockCode=:blockCode,@ParameterId=:parameterId ,@ClassId =:classId, @Year = :year,@Month = :month", nativeQuery = true)
    public List<MGMLParamMapper> getPercentageOfClassRoomMaterialAvailabilityReports(
            @Param("districtCode") String districtCode,
            @Param("blockCode") String blockCode,
            @Param("parameterId") String parameterId,
            @Param("year") String year,
            @Param("month") String month,
            @Param("classId") String classId
    );


    @Query(value = "EXEC Visit.rpt_UniqueSchoolCoverage @DistrictCode=:districtCode,@BlockCode=:blockCode,@Year = :year,@Month = :month", nativeQuery = true)
    public List<UniqueSchoolCoverageMapper> getUniqueSchoolCoverageReport(
            @Param("districtCode") String districtCode,
            @Param("blockCode") String blockCode,
            @Param("year") String year,
            @Param("month") String month
    );


    @Query(value = "EXEC Visit.rpt_UniqueVisitCoverage @DistrictCode=:districtCode,@BlockCode=:blockCode,@Year = :year,@Month = :month", nativeQuery = true)
    public List<UniqueVisitCoverageMapper> getUniqueVisitCoverageReport(
            @Param("districtCode") String districtCode,
            @Param("blockCode") String blockCode,
            @Param("year") String year,
            @Param("month") String month
            );


}