package com.netlink.rsk.visit_service.repository;

import com.netlink.rsk.visit_service.mapper.offline.ParameterWeekMapper;
import com.netlink.rsk.visit_service.model.VParameterWeekMapping;
import com.netlink.rsk.visit_service.queries.VisitQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParameterWeekMappingRepository extends JpaRepository<VParameterWeekMapping,Long>, JpaSpecificationExecutor<VParameterWeekMapping> {

    @Query(value = VisitQueries.GET_ALL_PARAMETER_WEEK_MAPPING_DATA,nativeQuery = true)
    public List<ParameterWeekMapper> getAllMappingData();

    @Query(value = VisitQueries.GET_ALL_PARAMETER_WEEK_MAPPING_DATA_BY_DATE,nativeQuery = true)
    public List<ParameterWeekMapper> getAllMappingDataByDate(@Param("LastUpdatedDate")String lastUpdatedDate);

    List<VParameterWeekMapping> findByWeekRangeIdAndVparameterMaster(Long weekRangeId, Long vparameterMaster);

}
