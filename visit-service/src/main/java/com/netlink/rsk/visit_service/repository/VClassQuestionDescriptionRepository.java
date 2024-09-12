package com.netlink.rsk.visit_service.repository;

import com.netlink.rsk.visit_service.mapper.offline.VClassQuestionDescription;
import com.netlink.rsk.visit_service.model.ClassQuestionDescription;
import com.netlink.rsk.visit_service.queries.VisitQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VClassQuestionDescriptionRepository extends JpaRepository<ClassQuestionDescription, Long> {
    List<ClassQuestionDescription> findByClassmaster_Classid(Long classid);

    @Query(value = VisitQueries.GET_ALL_CLASS_DESCRIPTION_BY_DATE,nativeQuery = true)
    public List<VClassQuestionDescription> getClassDescription(@Param("Date") String date);

    @Query(value = VisitQueries.GET_ALL_CLASS_DESCRIPTION,nativeQuery = true)
    public List<VClassQuestionDescription> getAllClassDescription();

}
