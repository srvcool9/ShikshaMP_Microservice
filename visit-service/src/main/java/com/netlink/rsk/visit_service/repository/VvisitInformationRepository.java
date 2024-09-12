package com.netlink.rsk.visit_service.repository;

import com.netlink.rsk.visit_service.model.VvisitInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VvisitInformationRepository  extends JpaRepository<VvisitInformation, Long> {
    VvisitInformation findByVisitUserSchoolSelfAllocationXref_Schoolallocationid(Long schoolallocationid);

    boolean existsByVisitUserSchoolSelfAllocationXref_Schoolallocationid(Long schoolallocationid);

}
