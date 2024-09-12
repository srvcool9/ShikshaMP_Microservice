package com.netlink.rsk.visit_service.repository;


import com.netlink.rsk.visit_service.mapper.offline.VParameterClassMapper;
import com.netlink.rsk.visit_service.model.VParameterClass;
import com.netlink.rsk.visit_service.queries.VisitQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VParameterClassRepository extends JpaRepository<VParameterClass,Long> {
    List<VParameterClass> findByClasses_IdAndVparameterMaster_Vparameterid(Long id, Long vparameterid);

   @Query(value = VisitQueries.GET_PARAMETER_CLASS_ROWS,nativeQuery = true)
    public List<VParameterClassMapper> getClassData();

    @Query(value = VisitQueries.GET_PARAMETER_CLASS_ROWS_DATE,nativeQuery = true)
    public List<VParameterClassMapper> getClassDataByDate(@Param("LastUpdateDate") String date);
}
