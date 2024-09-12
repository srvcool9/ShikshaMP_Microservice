package com.netlink.rsk.visit_service.studentdb.repositories;


import com.netlink.rsk.visit_service.model.SchoolMaster;
import com.netlink.rsk.visit_service.studentdb.models.StudentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentDetailsRepository extends JpaRepository<StudentDetails,Long> , JpaSpecificationExecutor<StudentDetails> {
//    List<StudentDetails> findByUdiseCode_UdiseCodeAndClassId(String udiseCode, Integer classId);
    public List<StudentDetails> findByUdiseCodeAndClassId(SchoolMaster schoolMaster, Integer classId);

    @Query(value = "select count(distinct Samagra) from Student.dbo.StudentDetail25\n" +
            " where GradeId=:ClassId and UDISECode=:UdiseCode",nativeQuery = true)
    public Long getStudentCountBySchoolCodeAndClass(@Param("ClassId")Long classId,
                                                    @Param("UdiseCode")String udiseCode);

//    List<StudentDetails> findByUdiseCode_UdiseCode(String udiseCode);



    @Query(value = "select * from Student.dbo.StudentDetail25 where Samagra=:Samagra",nativeQuery = true)
    public StudentDetails findBySamagra(@Param("Samagra")String samagra);

    List<StudentDetails> findStudentDetailsByUdiseCodeAndClassId(String udiseCode, Integer classId);

    List<StudentDetails> findByUdiseCode(String udiseCode);

//    @Query(value = "select * from Student.Dbo.StudentDetail25 where udisecode=:UdiseCode and ClassId<4",nativeQuery = true)
//    List<StudentDetails> getStudentsForUdise(@Param("UdiseCode") String udiseCode);

    @Transactional
    @Procedure("Student.dbo.GetFLNStudentDetail25")
    List<StudentDetails> getStudentsForUdise(@Param("UDISECode") String udiseCode);
}
