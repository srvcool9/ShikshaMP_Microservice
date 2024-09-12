package com.netlink.rsk.visit_service.repository;

import com.netlink.rsk.visit_service.model.VfeedbackResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VfeedbackResponseRepository extends JpaRepository<VfeedbackResponse, Long>, JpaSpecificationExecutor<VfeedbackResponse> {
    boolean existsByVschoolallocationid(int vschoolallocationid);
    long deleteByVschoolallocationid(int vschoolallocationid);
    VfeedbackResponse findByVschoolallocationid(int vschoolallocationid);
}