package com.netlink.rsk.visit_service.repository.spotassesment;


import com.netlink.rsk.visit_service.model.spotassesment.Spotassesment;
import com.netlink.rsk.visit_service.queries.SpotAssessmentQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface SpotAssesmentsRepository extends JpaRepository<Spotassesment, Long> {
    boolean existsBySchoolallocationidAllIgnoreCase(Long schoolallocationid);
    boolean existsBySchoolallocationid(Long schoolallocationid);

    Spotassesment findBySchoolallocationid(Integer schoolallocationid);
    List<Spotassesment> findBySchoolallocationid(Long schoolallocationid);

    Spotassesment findFirstBySchoolallocationid(Long schoolallocationid);
    List<Spotassesment> findByEmployeecodeAndRoleidAndIswebOrderBySpotassessmentidDesc(String employeecode, int roleid, Boolean isweb);


    @Transactional
    @Procedure("UDISE.up_GetSchoolbyUDISECode")
    public List<?> getUpGetSchoolByUDISECode(@Param("UserId")Long userId, @Param("RoleId")Long roleId, @Param("UDISECode")String UDISECode);

    @Query(value = SpotAssessmentQueries.GET_UDISE_LO_ASSESSMENT_DATE)
    public List<Spotassesment> getUDISECodeAndlearningoutcomeidAndAssesmentdate(
            String  UDISECode, Date assesmentdate
    );

    @Transactional
    @Modifying
    @Query(value = SpotAssessmentQueries.UPDATE_STATUS_BY_SPOT_ASSESSMENT_ID)
    int updateStatusBySpotassessmentid(String status, int spotassessmentid);

    @Query(value = SpotAssessmentQueries.GET_SPOT_ASSESSMENT_BY_EMPLOYEE_ROLE_UDISE_LO_DATE, nativeQuery = true)
    Spotassesment findByEmployeecodeAndRoleidAndUDISECode_UdiseCodeAndLearningoutcomeidAndAssesmentdateOrderBySpotassessmentidDesc(String employeecode, int roleid, String udiseCode);

     void deleteBySchoolallocationid(int schoolid);

     @Query(value = SpotAssessmentQueries.GET_TOTAL_SPOTS_FOR_MONTH_YEAR,nativeQuery = true)
            public Long getTotalStudentSpots(@Param("Month") Integer month,
                                     @Param("Year")Integer year);
}

