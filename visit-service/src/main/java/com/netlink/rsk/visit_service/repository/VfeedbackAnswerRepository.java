package com.netlink.rsk.visit_service.repository;


import com.netlink.rsk.visit_service.model.VfeedbackAnswer;
import com.netlink.rsk.visit_service.model.VfeedbackResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VfeedbackAnswerRepository extends JpaRepository<VfeedbackAnswer, Long> {
    long deleteByVfeedbackResponse(VfeedbackResponse vfeedbackResponse);
}