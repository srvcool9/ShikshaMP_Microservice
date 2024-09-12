package com.netlink.rsk.visit_service.repository.spotassesment;

import com.netlink.rsk.visit_service.model.spotassesment.Spotassesmentinfo;
import com.netlink.rsk.visit_service.model.spotassesment.Spotassesmentstudentinfo;
import com.netlink.rsk.visit_service.model.spotassesment.Spotassesmenttestresponse;
import com.netlink.rsk.visit_service.queries.SpotAssessmentQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface SpotAssesmentTestResponseRepository extends JpaRepository<Spotassesmenttestresponse,Long> {
    List<Spotassesmenttestresponse> findBySpotassesmentstudentinfoIn(Collection<Spotassesmentstudentinfo> spotassesmentstudentinfos);

    long deleteBySpotassesmentstudentinfoIn(Collection<Spotassesmentstudentinfo> spotassesmentstudentinfos);

    List<Spotassesmenttestresponse> findBySpotassesmentstudentinfo_SpotassessmentinfoIn(Collection<Spotassesmentinfo> spotassessmentinfos);
    List<Spotassesmenttestresponse> findBySpotassesmentstudentinfo_Spotassesmentstudentinfoid(Long spotassesmentstudentinfoid);

    @Query(value = SpotAssessmentQueries.GET_ANSWER_SMCQ_ID,nativeQuery = true)
    Integer findAnswerBySQMCQId(int smcqId);
}
