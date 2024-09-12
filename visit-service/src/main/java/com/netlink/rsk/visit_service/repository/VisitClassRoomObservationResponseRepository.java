package com.netlink.rsk.visit_service.repository;

import com.netlink.rsk.visit_service.mapper.BlockWiseReportMapper;
import com.netlink.rsk.visit_service.mapper.ClusterWiseReportMapper;
import com.netlink.rsk.visit_service.mapper.DistrictWiseReportMapper;
import com.netlink.rsk.visit_service.model.VisitClassRoomObservationResponse;
import com.netlink.rsk.visit_service.model.VisitUserSchoolSelfAllocationXref;
import com.netlink.rsk.visit_service.queries.VisitQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface VisitClassRoomObservationResponseRepository extends JpaRepository<VisitClassRoomObservationResponse, Long>, JpaSpecificationExecutor<VisitClassRoomObservationResponse> {
        List<VisitClassRoomObservationResponse> findByParameterValue_Vparametervalueid(Long vparametervalueid);
        long deleteByClassRoomObservationActivity_ClassRoomObservation_SchoolAllocation(VisitUserSchoolSelfAllocationXref schoolAllocation);
        boolean existsByClassRoomObservationActivity_ActivityId(Long activityId);

        @Query(value = VisitQueries.GET_CHECK_ACTIVITY_EXISTS,nativeQuery = true)
        public boolean checkActivityExists(@Param("ActivityId") Long activityId);

        List<VisitClassRoomObservationResponse> findByClassRoomObservationActivity_ActivityId(Long activityId);

        @Query(value = VisitQueries.GET_STATE_WISE_VISITS,nativeQuery = true)
        @Transactional
        List<DistrictWiseReportMapper> getStateWiseVisits(@Param("ParameterIds")List<Long> parameterIds,
                                                          @Param("Month")String month,
                                                          @Param("Year")String year);


        @Query(value = VisitQueries.GET_DISTRICT_WISE_VISITS,nativeQuery = true)
        @Transactional
        List<BlockWiseReportMapper> getDistrictWiseVisits(@Param("ParameterIds")List<Long> parameterIds,
                                                          @Param("Id")String id,
                                                          @Param("Month")String month,
                                                          @Param("Year")String year);


        @Transactional
        @Query(value = VisitQueries.GET_CLUSTER_WISE_VISITS,nativeQuery = true)
        public List<ClusterWiseReportMapper> getClusterWiseReport(@Param("ParameterIds")List<Long> parameterIds,
                                                                  @Param("Id")String id,
                                                                  @Param("Month")String month,
                                                                  @Param("Year")String year);
}
