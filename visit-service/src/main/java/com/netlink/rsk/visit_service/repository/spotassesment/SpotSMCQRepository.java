package com.netlink.rsk.visit_service.repository.spotassesment;


import com.netlink.rsk.visit_service.model.spotassesment.SpotAssmentSMCQ;
import com.netlink.rsk.visit_service.queries.SpotAssessmentQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpotSMCQRepository extends JpaRepository<SpotAssmentSMCQ,Long> {


    @Query(value = SpotAssessmentQueries.GET_SPOT_RANDOM_QUESTIONS,nativeQuery = true)
    public List<SpotAssmentSMCQ> getRandomQuestions(@Param(value = "subjectId") Long subjectId,  @Param(value = "weekid")int weekid);
}
