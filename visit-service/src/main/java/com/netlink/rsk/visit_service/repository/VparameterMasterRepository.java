package com.netlink.rsk.visit_service.repository;


import com.netlink.rsk.visit_service.mapper.DashboardReports.BlockWiseMapper;
import com.netlink.rsk.visit_service.mapper.DashboardReports.MonthWiseParameterReportMapper;
import com.netlink.rsk.visit_service.mapper.MentoringVisitParameterMapper;
import com.netlink.rsk.visit_service.mapper.StateLMapper;
import com.netlink.rsk.visit_service.mapper.offline.VisitParameters;
import com.netlink.rsk.visit_service.model.VparameterMaster;
import com.netlink.rsk.visit_service.queries.VisitQueries;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface VparameterMasterRepository extends JpaRepository<VparameterMaster, Long> {
    List<VparameterMaster> findByvsubsectionid(int vsubsectionid);

    List<VparameterMaster> findByVsubSectionMaster_Vsubsectionid(Long vsubsectionid, Sort sort);

    @Query(value = VisitQueries.GET_PARAMETER_BY_SUB_SECTION_ID,nativeQuery = true)
    public List<MentoringVisitParameterMapper> getParametersBySubSectionId(@Param("VSubSectionId") Long ssId,
                                                                           @Param("CompetencyId")Long competencyId);


    List<VparameterMaster> findByVsubsectionidOrderByOrdernumberAsc(int vsubsectionid);

    List<VparameterMaster> findByDomainmasteridAndIsactive(Integer domainmasterid, boolean isactive);

    @Query(value = VisitQueries.GET_PARAMETER_NAME_BY_ID,nativeQuery = true)
    List<Long> getParameterIdByName(@Param("ParameterName") String parameterName);


    @Query(value = VisitQueries.GET_PARAMETER_BY_SECTION_ID_AND_NAME,nativeQuery = true)
    public Long getParameterIdBySectionAndName(@Param("ParameterName") String parameterName,
                                               @Param("VSectionId")Long sectionId);

    public VparameterMaster findByVsubSectionMaster_Vsectionmaster_VsectionidAndParametername(Long vsectionid, String parametername);


    @Query(value = VisitQueries.GET_DISTRICT_PERCENTAGE,nativeQuery = true)
    public BlockWiseMapper getDistrictPercentage(@Param("ParamId")Long paramId,
                                                 @Param("DistrictId")String districtId,
                                                 @Param("Year")Integer year,
                                                 @Param("Month")Integer month);


    @Query(value =VisitQueries.GET_BLOCK_PERCENTAGE,nativeQuery = true)
    public List<BlockWiseMapper> getBlocksPercentage(@Param("ParamId")Long paramId,
                                                     @Param("DistrictId")String districtId,
                                                     @Param("Year")Integer year,
                                                     @Param("Month")Integer month);

    @Query(value = VisitQueries.GET_DISTRICT_VISIT_REPORT_FOR_PARAMETER,nativeQuery = true)
    public List<StateLMapper> getAllDistrictsVisitReportForParameter(@Param("ParameterId") Long parameterId,
                                                                     @Param("Month") Integer month,
                                                                     @Param("Year") Integer year);

    @Query(value = VisitQueries.GET_CLUSTER_REPORT_BY_PARAMETER,nativeQuery = true)
    public List<StateLMapper> getAllClustersReportForParameterByBlock(@Param("BlockId")String blockId,
                                                                      @Param("ParameterId") Long parameterId,
                                                                      @Param("Month")Integer month,
                                                                      @Param("Year")Integer year);

    @Query(value = VisitQueries.GET_MONTHLY_STATE_REPORT,nativeQuery = true)
    public List<MonthWiseParameterReportMapper> getMonthlyStateReport(@Param("Param")Long parameterId,
                                                                      @Param("Year") Integer year);

    @Query(value = VisitQueries.GET_MONTHLY_DISTRICT_REPORT,nativeQuery = true)
    public List<MonthWiseParameterReportMapper> getMonthlyDistrictReport(@Param("Param")Long parameterId,
                                                                         @Param("Year") Integer year,
                                                                         @Param("DistrictId")String districtId);

    @Query(value =VisitQueries.GET_MONTHLY_BLOCK_REPORT ,nativeQuery = true)
    public List<MonthWiseParameterReportMapper> getMonthlyBlockReport(@Param("Param")Long parameterId,
                                                                         @Param("Year") Integer year,
                                                                      @Param("BlockId")String blockId);

    @Query(value = VisitQueries.GET_MONTHLY_CLUSTER_REPORT,nativeQuery = true)
    public List<MonthWiseParameterReportMapper> getMonthlyClusterReport(@Param("Param")Long parameterId,
                                                                      @Param("Year") Integer year,
                                                                        @Param("ClusterCode")String clusterId);

    @Query(value = VisitQueries.CHECK_IF_PARAMETER_OF_CLASS,nativeQuery = true)
    public List<Object> checkIfParameterOfClass(@Param("ParamId")Long paramId,
                                                @Param("ClassId")Long classId);


    @Query(value = VisitQueries.GET_ALL_PARAMETER_ROWS,nativeQuery = true)
    public List<VisitParameters> getAllParameters();

    @Query(value = VisitQueries.GET_ALL_PARAMETER_ROWS_BY_DATE,nativeQuery = true)
    public List<VisitParameters> getAllParametersByDate(@Param("LastUpdatedOn")String date);

    @Query(value = VisitQueries.CHANGE_PARAMETER_STATUS,nativeQuery = true)
    @Transactional
    @Modifying
    public int changeStatus(@Param("ParameterId") Long paramId,@Param("Status")int status);

    @Transactional
    @Modifying
    @Query(value = VisitQueries.UPDATE_PARAMETER_ORDERNO,nativeQuery = true)
    public int updateParameterOrderNumber(@Param("Vparameterid")Long parameterId,
                                          @Param("OrderNo")Long orderNo);


}