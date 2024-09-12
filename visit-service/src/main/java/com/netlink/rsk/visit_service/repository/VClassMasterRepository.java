package com.netlink.rsk.visit_service.repository;


import com.netlink.rsk.visit_service.mapper.offline.VisitClassMapper;
import com.netlink.rsk.visit_service.model.Classmaster;
import com.netlink.rsk.visit_service.queries.VisitQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VClassMasterRepository extends JpaRepository<Classmaster, Long> {
    boolean existsByQuestionAndClassid(String question, Long classid);

    List<Classmaster> findAll();

    @Query(value = VisitQueries.GET_ALL_QUESTION_NAME_BY_CLASSID,nativeQuery = true)
    List<Long> findByQuestionNames(@Param("QuestionName") String questionName);

    @Query(value = VisitQueries.GET_ALL_CLASSES,nativeQuery = true)
    public List<VisitClassMapper> fetchAllClasses();

}
