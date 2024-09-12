package com.netlink.rsk.visit_service.repository.spotassesment;

import com.netlink.rsk.visit_service.mapper.spotass.CompetencyQuestionPickerMapper;
import com.netlink.rsk.visit_service.model.spotassesment.QuestionPicker;
import com.netlink.rsk.visit_service.queries.SpotAssessmentQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionPickerRepository extends JpaRepository<QuestionPicker,Long>, JpaSpecificationExecutor<QuestionPicker> {

  @Query(value = SpotAssessmentQueries.GET_DOMAIN_COMPETENCY_MAPPING_DATA,nativeQuery = true)
    public List<CompetencyQuestionPickerMapper> getCompetency(@Param("ClassId")Long classId, @Param("SubjectId")Long subjectId,
                                                              @Param("WeekRangeId")Long weekRangeId);

  public QuestionPicker findByWeekIdAndCompetencyIdAndNoOfQuestions(Long weekId, Long competencyId, Integer noOfQuestions);
}
