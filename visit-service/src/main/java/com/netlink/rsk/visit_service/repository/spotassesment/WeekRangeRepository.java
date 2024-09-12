package com.netlink.rsk.visit_service.repository.spotassesment;


import com.netlink.rsk.visit_service.model.spotassesment.Weekmaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WeekRangeRepository  extends JpaRepository<Weekmaster,Long> , JpaSpecificationExecutor<Weekmaster> {
}
