package com.netlink.rsk.visit_service.repository.spotassesment;


import com.netlink.rsk.visit_service.model.spotassesment.SpotSubjectMaster;
import com.netlink.rsk.visit_service.queries.SpotAssessmentQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SpotSubjectMasterRepository extends JpaRepository<SpotSubjectMaster,Long> , JpaSpecificationExecutor<SpotSubjectMaster> {

    @Query(value = SpotAssessmentQueries.UPDATE_SUBJECT_STATUS,nativeQuery = true)
    @Transactional
    @Modifying
    public int updateStatus(@Param("SubjectId") Long subjectId,
                            @Param("Status") Boolean status);

    List<SpotSubjectMaster> findByIsActiveTrue();




}