package com.netlink.rsk.visit_service.repository;


import com.netlink.rsk.visit_service.model.CompetencyParameterTransaction;
import com.netlink.rsk.visit_service.queries.VisitQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CompetencyParameterTransactionRepository extends JpaRepository<CompetencyParameterTransaction,Long>, JpaSpecificationExecutor<CompetencyParameterTransaction> {

    @Query(value = VisitQueries.GET_ALL_COMPETENCY_PARAMETERS_TRANSACTION_BY_DATE,nativeQuery = true)
    List<CompetencyParameterTransaction> getAllCompetencyParameterByDate(@Param("LastUpdatedOn")String date);

    List<CompetencyParameterTransaction> findByCompetencyId(Long competencyId);

}
