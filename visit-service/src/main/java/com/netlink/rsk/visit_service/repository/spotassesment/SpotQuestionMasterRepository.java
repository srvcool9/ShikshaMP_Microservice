package com.netlink.rsk.visit_service.repository.spotassesment;


import com.netlink.rsk.visit_service.model.spotassesment.SpotQuestionMaster;
import com.netlink.rsk.visit_service.queries.SpotAssessmentQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SpotQuestionMasterRepository  extends JpaRepository<SpotQuestionMaster,Long> {
    @Query(value = SpotAssessmentQueries.GET_RANDOM_QUESTIONS_BY_WEEK_SUBJECT_CLASS,nativeQuery = true)
    List<SpotQuestionMaster> findByClassIdAndSubjectIdAndWeekRangeWeekidAndDomainMasterDomainIdAndCompetencyMasterCompetencyMaster(@Param(value = "classId")Long classId, @Param(value = "subjectId") Long subjectId, @Param(value = "weekid") Long weekid);

    long deleteByQuestionId(Long questionId);
   List<SpotQuestionMaster> findByClassIdAndSubjectIdAndWeekRange_WeekidAndDomainMaster_DomainIdAndCompetencyMaster_CompetencyMasterAndIsActive(Long classId, Long subjectId, Long weekid, Long domainId, Long competencyMaster,Boolean isActive);

    @Query(value = SpotAssessmentQueries.DEACTIVATE_QUESTIONS,nativeQuery = true)
    @Transactional
    @Modifying
    public void deactivateQuestion(@Param(value = "questionId")Long questionId);

    @Transactional
    @Procedure("SpotAss.GetQuestions")
    List<SpotQuestionMaster> getRandomQuestions(@Param("WeekId") Long  weekId,
                                                @Param("ClassId") Long  classId,
                                                @Param("SubjectId") Long  subjectId);

    SpotQuestionMaster findByCompetencyMaster_CompetencyMasterAndQuestionId(Long competencyMaster, Long questionId);


    @Query(value = SpotAssessmentQueries.GET_ALL_QUESTIONS_DATE,nativeQuery = true)
    public List<SpotQuestionMaster> getAllQuestionByDate(@Param("LastUpdatedDate")String date);

    List<SpotQuestionMaster> findByIsActiveTrue();


}
