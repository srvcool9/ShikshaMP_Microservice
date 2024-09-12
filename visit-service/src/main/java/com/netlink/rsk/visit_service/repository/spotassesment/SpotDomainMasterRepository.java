package com.netlink.rsk.visit_service.repository.spotassesment;

import com.netlink.rsk.visit_service.mapper.spotass.CompetencyMapperData;
import com.netlink.rsk.visit_service.mapper.spotass.SpotDomainMapper;
import com.netlink.rsk.visit_service.model.spotassesment.SpotDomainMaster;
import com.netlink.rsk.visit_service.queries.SpotAssessmentQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SpotDomainMasterRepository  extends JpaRepository<SpotDomainMaster,Long> {
    public List<SpotDomainMaster> findByClassIdAndSubjectId(Long classId, Long subjectId);
    @Query(value = SpotAssessmentQueries.GET_ALL_COMPETENCY_LIST,nativeQuery = true)
    @Transactional
    public List<CompetencyMapperData> getAllCompetencyList();

    @Query(value = SpotAssessmentQueries.GET_ALL_DOMAIN_LIST,nativeQuery = true)
    public List<SpotDomainMapper> getDomainList();

    @Transactional
    @Modifying
    @Query(value = SpotAssessmentQueries.UPDATE_STATUS_FOR_DOMAIN,nativeQuery = true)
    public int updateStatus(@Param("DomainId")Long domainId,
                            @Param("Status")Boolean status);

    public List<SpotDomainMaster> findBySubjectId(Long subjectId);

    @Query(value =SpotAssessmentQueries.GET_ALL_DOMAINS,nativeQuery = true)
    public List<SpotDomainMapper> getAllDomains();


}
