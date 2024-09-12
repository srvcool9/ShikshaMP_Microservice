package com.netlink.rsk.visit_service.repository.spotassesment;


import com.netlink.rsk.visit_service.mapper.offline.CompetencyMasterMapper;
import com.netlink.rsk.visit_service.model.spotassesment.CompetencyMaster;
import com.netlink.rsk.visit_service.queries.SpotAssessmentQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CompetencyMasterRepository extends JpaRepository<CompetencyMaster,Long> {

    @Query(value = SpotAssessmentQueries.GET_COMPETENCYS,nativeQuery = true)
    public List<CompetencyMasterMapper> getCompetencys();

    @Transactional
    @Modifying
    @Query(value = SpotAssessmentQueries.UPDATE_COMPETENCY_STATUS,nativeQuery = true)
    public int updateStatus(@Param("CompetencyId")Long competencyId,
                            @Param("Status") Boolean status);


    @Query(value = SpotAssessmentQueries.GET_ALL_COMPETENCY,nativeQuery = true)
    public List<com.netlink.rsk.visit_service.mapper.offline.CompetencyMasterMapper> getAllCompetencys();

    @Query(value = SpotAssessmentQueries.GET_COMPETENCYS_BY_DATE,nativeQuery = true)
    public List<com.netlink.rsk.visit_service.mapper.offline.CompetencyMasterMapper> getAllCompetencysByDate(@Param("LastUpdatedDate")String date);

}
