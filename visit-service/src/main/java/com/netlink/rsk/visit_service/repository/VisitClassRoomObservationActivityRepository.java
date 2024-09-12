package com.netlink.rsk.visit_service.repository;

import com.netlink.rsk.visit_service.constants.StaticResponse;
import com.netlink.rsk.visit_service.mapper.LOProgressionMapper;
import com.netlink.rsk.visit_service.model.VisitClassRoomObservationActivity;
import com.netlink.rsk.visit_service.queries.VisitQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitClassRoomObservationActivityRepository extends JpaRepository<VisitClassRoomObservationActivity, Long>, JpaSpecificationExecutor<VisitClassRoomObservationActivity> {
    long deleteByClassRoomObservation_SchoolAllocation_Schoolallocationid(Long schoolallocationid);
    VisitClassRoomObservationActivity findFirstByDomain_DomainidAndClassRoomObservation_ClassRoomObservationId(int domainid, Long classRoomObservationId);

    @Query(value = VisitQueries.GET_FIRST_ACTIVITY_BY_DOMAIN_AND_OBSERVATION,nativeQuery = true)
    VisitClassRoomObservationActivity getFirstActivityByDomainAndObservation(@Param("DomainId")int domainId,
                                                                             @Param("ObservationId")Long observationId);
    VisitClassRoomObservationActivity findFirstBySubSection_VsubsectionidAndClassRoomObservation_ClassRoomObservationId(Long vsubsectionid, Long classRoomObservationId);
    VisitClassRoomObservationActivity findBySectionMaster_VsectionidAndClassRoomObservation_ClassRoomObservationId(Long vsectionid, Long classRoomObservationId);

    VisitClassRoomObservationActivity findFirstBySectionMaster_VsectionidAndSubSection_VsubsectionidAndClassRoomObservation_ClassRoomObservationId(Long vsectionid, Long vsubsectionid, Long classRoomObservationId);

    List<VisitClassRoomObservationActivity> findBySubSection_VsubsectionidAndClassRoomObservation_ClassRoomObservationId(Long vsubsectionid, Long classRoomObservationId);

   @Query(value = StaticResponse.LO_PROGRESSION_DISTRICT_REPORT,nativeQuery = true)
   public List<LOProgressionMapper> getLOProgressionDistrictReport(@Param("Year") Long year,
                                                                   @Param("Month") Long month,
                                                                   @Param("SubjectId")Long subjectId);

    @Query(value = StaticResponse.LO_PROGRESSION_BLOCK_REPORT,nativeQuery = true)
    public List<LOProgressionMapper> getLOProgressionBlockReport(@Param("Year") Long year,
                                                                    @Param("Month") Long month,
                                                                    @Param("SubjectId")Long subjectId,
                                                                 @Param("AreaId")String areaId);
    @Query(value = StaticResponse.LO_PROGRESSION_CLUSTER_REPORT,nativeQuery = true)
    public List<LOProgressionMapper> getLOProgressionClusterReport(@Param("Year") Long year,
                                                                 @Param("Month") Long month,
                                                                 @Param("SubjectId")Long subjectId,
                                                                   @Param("AreaId")String areaId);
}
