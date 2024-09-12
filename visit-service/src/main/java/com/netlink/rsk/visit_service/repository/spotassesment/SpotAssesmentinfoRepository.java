package com.netlink.rsk.visit_service.repository.spotassesment;


import com.netlink.rsk.visit_service.mapper.spotass.StudentResponseMapper;
import com.netlink.rsk.visit_service.model.SubjectMaster;
import com.netlink.rsk.visit_service.model.spotassesment.Spotassesmentinfo;
import com.netlink.rsk.visit_service.queries.SpotAssessmentQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SpotAssesmentinfoRepository extends JpaRepository<Spotassesmentinfo,Long> {
    boolean existsBySpotassessment_SpotassessmentidAndClassidAndSubject(Long spotassessmentid, int classid, SubjectMaster subject);
    List<Spotassesmentinfo> findByClassidAndSubjectType(int classid, Long subjectType);
    List<Spotassesmentinfo> findBySpotassessment_Spotassessmentid(Long spotassessmentid);

    long countBySpotassessment_Spotassessmentid(Long spotassessmentid);


    @Query(value = SpotAssessmentQueries.GET_SPOT_ASSESSMENT_INFO_COUNT,nativeQuery = true)
    long countByClassidAndSpotassessment_SpotassessmentidAndSubjectMaster_Subjectid(int classid, Long spotassessmentid);

    Spotassesmentinfo findFirstBySpotassessment_Spotassessmentid(Long spotassessmentid);

    Spotassesmentinfo findBySpotassessment_SpotassessmentidAndClassidAndSubject_SubjectId(Long spotassessmentid, int classid, Long subjectId);



    Spotassesmentinfo findBySpotassessment_SpotassessmentidAndClassid(Long spotassessmentid, int classid);


    Spotassesmentinfo findBySpotassessment_SpotassessmentidAndClassidAndSubject_SubjectIdAndWeekid(Long spotassessmentid, int classid, Long subjectId,int weekid);

   @Query(value =SpotAssessmentQueries.GET_STUDENT_RESPONSE_BY_SPOT_SAMAGRA ,nativeQuery = true )
    @Transactional
    public List<StudentResponseMapper> getStudentResponseBySpotAndSamagra(@Param(value = "SpotAssessmentId")Long spotAssessmentId,
                                                                          @Param(value = "SubjectId")Long subjectId,
                                                                          @Param(value = "SamagraId")String samagraId );

}
