package com.netlink.rsk.visit_service.repository;

import com.netlink.rsk.visit_service.model.VRegularVisitSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface VRegularVisitScheduleRepository extends JpaRepository<VRegularVisitSchedule,Long>, JpaSpecificationExecutor<VRegularVisitSchedule> {
}
