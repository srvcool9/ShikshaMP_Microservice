package com.netlink.rsk.visit_service.repository;

import com.netlink.rsk.visit_service.model.ClassMasterTransaction;
import com.netlink.rsk.visit_service.queries.VisitQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ClassMasterTransactionRepository extends JpaRepository<ClassMasterTransaction, Long>, JpaSpecificationExecutor<ClassMasterTransaction> {
    long deleteByClassMasterId(Long classMasterId);
    List<ClassMasterTransaction> findByClassMasterId(Long classMasterId);

    @Query(value = VisitQueries.GET_ALL_CLASS_MASTER_TRANS,nativeQuery = true)
    public List<ClassMasterTransaction> getAllClassMasterTrans(@Param("Date")String date);
}