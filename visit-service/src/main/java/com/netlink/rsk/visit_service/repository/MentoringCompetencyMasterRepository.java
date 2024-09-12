package com.netlink.rsk.visit_service.repository;

import com.netlink.rsk.visit_service.model.MentoringCompetencyMaster;
import com.netlink.rsk.visit_service.queries.VisitQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MentoringCompetencyMasterRepository extends JpaRepository<MentoringCompetencyMaster,Long>, JpaSpecificationExecutor<MentoringCompetencyMaster> {
    boolean existsByCompetencyName(String competencyName);
    

    @Query(value = "select distinct d.DomainName\n" +
            "from Visit.MentoringCompetencyMaster c \n" +
            "inner join Visit.MentoringDomainMaster d on d.domainid=c.DomainId\t\n" +
            "where c.CompetencyId=:CompId\n",nativeQuery = true)
    public String getDomain(@Param("CompId")long compId);

    @Query(value = "select distinct s.Name \n" +
            "from Visit.MentoringCompetencyMaster c \n" +
            "inner join Visit.MentoringDomainMaster d on d.domainid=c.DomainId\t\n" +
            "inner join Visit.VsectionMaster s on s.VsectionId=d.VsectionId\n" +
            "where c.CompetencyId=:CompId",nativeQuery = true)
    public String getSection(@Param("CompId")Long compId);

    @Query(value = "select distinct s.VsectionId \n" +
            "from Visit.MentoringCompetencyMaster c \n" +
            "inner join Visit.MentoringDomainMaster d on d.domainid=c.DomainId\t\n" +
            "inner join Visit.VsectionMaster s on s.VsectionId=d.VsectionId\n" +
            "where c.CompetencyId=:CompId",nativeQuery = true)
    public Long getSectionId(@Param("CompId")Long compId);
    @Query(value = VisitQueries.GET_ALL_MENTORING_COMPETENCY_MASTER_BY_DATE,nativeQuery = true)
    List<MentoringCompetencyMaster> getAllMentoringCompetencyMasterByDate(@Param("LastUpdatedOn")String date);

    @Transactional
    @Modifying
    @Query(value = VisitQueries.UPDATE_MENTORING_COMPETENCY_STATUS,nativeQuery = true)
    public int updateCompetencyStatus(@Param("CompetencyId")Long competencyId,
                                      @Param("Status")Boolean status);

    boolean existsByCompetencyNameAndDomainId(String competencyName, Long domainId);


    @Transactional
    @Modifying
    @Query(value = VisitQueries.UPDATE_MENTORING_COMPETENCY_ORDER_NUMBER,nativeQuery = true)
    public int updateMentoringCompetencyOrderNumber(@Param("CompetencyId")Long domainId,
                                                    @Param("OrderNo")Long orderNo);
}
