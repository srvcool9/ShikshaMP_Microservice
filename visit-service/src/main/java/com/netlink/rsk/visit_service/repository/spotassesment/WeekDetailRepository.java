package com.netlink.rsk.visit_service.repository.spotassesment;


import com.netlink.rsk.visit_service.mapper.offline.WeekDetailMapper;
import com.netlink.rsk.visit_service.model.spotassesment.WeekDetail;
import com.netlink.rsk.visit_service.queries.SpotAssessmentQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WeekDetailRepository extends JpaRepository<WeekDetail,Long> {

    @Query(value = SpotAssessmentQueries.GET_ALL_WEEK_DETAIL,nativeQuery = true)
    public List<WeekDetailMapper> getAllWeekDetails();
}
