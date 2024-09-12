package com.netlink.rsk.visit_service.repository;


import com.netlink.rsk.visit_service.model.VfeedbackParameterMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VfeedbackParameterMasterRepository extends JpaRepository<VfeedbackParameterMaster, Long>, JpaSpecificationExecutor<VfeedbackParameterMaster> {

}