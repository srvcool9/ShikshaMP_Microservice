package com.netlink.rsk.visit_service.repository;


import com.netlink.rsk.visit_service.mapper.ParamMapper;
import com.netlink.rsk.visit_service.mapper.offline.VisitDomainMapper;
import com.netlink.rsk.visit_service.model.Domainmaster;
import com.netlink.rsk.visit_service.queries.VisitQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VDomainMasterRepository extends JpaRepository<Domainmaster, Long> {
    List<Domainmaster> findByVsubSectionMaster_Vsubsectionid(Long vsubsectionid);


    @Query(value = VisitQueries.GET_ALL_PARAMETERS,nativeQuery = true)
    public List<ParamMapper> haveParameters(@Param("DomainId")int domainId,
                                            @Param("ClassId") Long classId
    );

    @Query(value = VisitQueries.GET_ALL_DOMAIN_LIST,nativeQuery = true)
    public List<VisitDomainMapper> getDomainList();

    @Query(value = VisitQueries.GET_ALL_DOMAIN_LIST_BY_DATE,nativeQuery = true)
    public List<VisitDomainMapper> getDomainListByDate(@Param("LastUpdatedDate")String date);



    @Query(value = VisitQueries.GET_ACTIVE_DOMIANS_BY_SUB_SECTION,nativeQuery = true)
    public List<Domainmaster> getActiveDomainsBySubsection(@Param("SubSectionId")Long subSectionId);

}