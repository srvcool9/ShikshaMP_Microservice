package com.netlink.rsk.visit_service.repository.spotassesment;

import com.netlink.rsk.visit_service.mapper.spotass.StudentDataMapper;
import com.netlink.rsk.visit_service.model.spotassesment.Spotassesmentinfo;
import com.netlink.rsk.visit_service.model.spotassesment.Spotassesmentstudentinfo;
import com.netlink.rsk.visit_service.queries.SpotAssessmentQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Repository
public interface SpotAssesmentStudnetInfoRepository extends JpaRepository<Spotassesmentstudentinfo,Long> {
    List<Spotassesmentstudentinfo> findBySpotassessmentinfo_ClassidAndSpotassessmentinfo_SubjectType(int classid, Long subjectType);
    List<Spotassesmentstudentinfo> findBySpotassessmentinfo_Spotassessmentinfoid(Long spotassessmentinfoid);

    List<Spotassesmentstudentinfo> findBySpotassessmentinfoIn(Collection<Spotassesmentinfo> spotassessmentinfos);
    long countBySpotassessmentinfoIn(Collection<Spotassesmentinfo> spotassessmentinfos);
    @Query(value = SpotAssessmentQueries.GET_SPOT_STUDENT_INFO_BY_SAMAGRA_INFO_ID, nativeQuery = true)
    long existsByStudentDetailAllAndSpotassessmentinfo_SpotassessmentinfoidAndCreatedon(Long studentDetailAll, Long spotassessmentinfoid);
    long countBySpotassessmentinfo_Spotassessmentinfoid(int spotassessmentinfoid);

    long countBySpotassessmentinfo_Spotassessment_Spotassessmentid(Long spotassessmentid);

    @Query(value = SpotAssessmentQueries.GET_SPOT_STUDENT_DATA, nativeQuery = true)
    @Transactional
     List<StudentDataMapper> getStudentData(@Param(value = "SpotAssessmentId")Long spotAssessmentId,
                                            @Param(value = "SubjectId") Long subjectId);


    @Query(value = SpotAssessmentQueries.GET_STUDENT_DATA_COMPETENCY_WISE,nativeQuery = true)
    @Transactional
    List<StudentDataMapper> getStudentDataCompetencyWise(@Param(value = "SpotAssessmentId")Long spotAssessmentId,
                                                         @Param(value = "SubjectId") Long subjectId,
                                                         @Param(value = "CompetencyId")Long competencyId);
}
