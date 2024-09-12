package com.netlink.rsk.visit_service.repository;

import com.netlink.rsk.visit_service.mapper.offline.ParameterValueMapper;
import com.netlink.rsk.visit_service.model.VparameterValue;
import com.netlink.rsk.visit_service.queries.VisitQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VparameterValuesRepository  extends JpaRepository<VparameterValue,Long>, JpaSpecificationExecutor<VparameterValue> {
    List<VparameterValue> findByVparameterMaster_Vparameterid(Long vparameterid);

    @Query(value = VisitQueries.GET_PARAMETERID_BY_SUBJECT_NAME,nativeQuery = true)
    public Long getParameterIdBySubjectName(@Param("VSectionId")Long sectionId,
                                            @Param("VparameterName")String parameterName);

    @Query(value = VisitQueries.GET_ALL_PARAMETER_VALUES_ROWS,nativeQuery = true)
    public List<ParameterValueMapper> getParameterValues();

    @Query(value = VisitQueries.GET_PARAMETER_VALUES_BY_DATE_FILTER,nativeQuery = true)
    public List<ParameterValueMapper> getParameterValuesByDate(@Param("LastUpdatedDate")String date);


}
