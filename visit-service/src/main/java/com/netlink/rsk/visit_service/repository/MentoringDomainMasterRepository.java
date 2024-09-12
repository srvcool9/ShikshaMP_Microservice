package com.netlink.rsk.visit_service.repository;


import com.netlink.rsk.visit_service.mapper.MentoringCompetencyMasterMapper;
import com.netlink.rsk.visit_service.model.MentoringDomainMaster;
import com.netlink.rsk.visit_service.queries.VisitQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MentoringDomainMasterRepository extends JpaRepository<MentoringDomainMaster,Long>, JpaSpecificationExecutor<MentoringDomainMaster> {

    boolean existsByDomainName(String domainName);

    @Query(value = VisitQueries.GET_MENTORING_DOMAIN_BY_SECTION,nativeQuery = true)
    public List<MentoringDomainMaster> getMentoringDomainsBySectionId(@Param("SectionId")Long SectionId);

    @Query(value = VisitQueries.GET_DOMAIN_BY_SECTION,nativeQuery = true)
    List<MentoringDomainMaster> getBySectionId(@Param("SectionId") Long vSectionId);

    @Query(value = VisitQueries.GET_COMPETENCY_BY_DOMAIN,nativeQuery = true)
    List<MentoringCompetencyMasterMapper> getCompetencyByDomain(@Param("DomainId")Long domainId);

    @Query(value = VisitQueries.GET_ALL_MENTORING_DOMAINS_BY_DATE,nativeQuery = true)
    List<MentoringDomainMaster> getAllDomainsByDate(@Param("LastUpdatedOn")String date);

    @Transactional
    @Modifying
    @Query(value =VisitQueries.UPDATE_MENTORING_DOMAIN_STATUS ,nativeQuery = true)
    int updateDomainIdStatus(@Param("DomainId") Long domainId,@Param("Status") Boolean isActive);

    @Transactional
    @Modifying
    @Query(value = VisitQueries.UPDATE_MENTORING_DOMAIN_OREDER_NUMBER,nativeQuery = true)
    public int updateMentoringDomainOrderNumber(@Param("DomainId")Long domainId,
                                          @Param("OrderNo")Long orderNo);

}
