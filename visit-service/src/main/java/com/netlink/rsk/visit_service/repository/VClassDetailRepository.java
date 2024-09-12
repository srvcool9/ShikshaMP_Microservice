package com.netlink.rsk.visit_service.repository;

import com.netlink.rsk.visit_service.model.VClassDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VClassDetailRepository extends JpaRepository<VClassDetail, Long> {
    VClassDetail findBySchoolallocationid(int schoolallocationid);
    boolean existsBySchoolallocationid(int schoolallocationid);

    long deleteBySchoolallocationid(int schoolallocationid);

}