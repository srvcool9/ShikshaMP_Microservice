package com.netlink.rsk.visit_service.employeedb.repositories;


import com.netlink.rsk.visit_service.employeedb.models.EmployeeMaster;
import com.netlink.rsk.visit_service.mapper.EmpMapper;
import com.netlink.rsk.visit_service.mapper.EmployeeAreaMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeMasterRepository extends JpaRepository<EmployeeMaster, Long>, JpaSpecificationExecutor<EmployeeMaster> {
    List<EmployeeMaster> findByUDISECode(String UDISECode);
    List<EmployeeMaster> findByEmployeeCodeLike(String employeeCode);

    @Query(value = "SELECT TOP(10) EmployeeCode\n" +
            "FROM Employee.EmployeeMaster emp\n" +
            "WHERE EmployeeCode LIKE :EmpCode%",nativeQuery = true)
    List<String> getEmpSearchResults(@Param("EmpCode")String empCode);


    public EmployeeMaster findByEmployeeCode(String empCode);

    @Query(value = "select e.EmployeeCode,e.EmployeeName,s.UDISECode, s.SchoolName,Cast(c.ClusterCode as nvarchar) as ClusterId,c.ClusterName,b.BlockId,b.BlockName,dc.DistrictId,dc.DistrictName,\n" +
            "dv.DivisionId,dv.DivisionName\n" +
            "from Employee.Dbo.EmployeeMaster e\n" +
            "inner join UDISE.SchoolMaster s on  e.UDISECode=s.UDISECode\n" +
            "inner join UDISE.ClusterMaster c on s.ClusterCode=c.ClusterCode\n" +
            "inner join UDISE.BlockMaster b on b.BlockId=c.BlockCode\n" +
            "inner join UDISE.DistrictMaster dc on dc.DistrictId=b.DistrictId\n" +
            "inner join UDISE.DivisionMaster dv on dv.DivisionId=dc.DivisionId\n" +
            "where e.EmployeeCode= :EmployeeCode ",nativeQuery = true)
    public EmployeeAreaMapper getEmployeeArea(@Param(value = "EmployeeCode") String empCode);


    @Query(value = "select emp.EmployeeCode,emp.EmployeeName,emp.Designation,emp.UDISECode,sm.SchoolName,sm.ClusterId,sm.ClusterName, \n" +
            "                        sm.BlockId,sm.BlockName,sm.DistrictId,sm.DistrictName \n" +
            "                         FROM Employee.dbo.EmployeeMaster emp  \n" +
            "                        left join Shiksha.UDISE.SchoolMaster sm on sm.UDISECode=emp.UDISECode \n" +
            "                        where emp.EmployeeCode=:EmpCode",nativeQuery = true)
    public EmpMapper getEmployeeDetails(@Param("EmpCode")String empCode);

    @Query(value = "select distinct sm.SchoolName\n" +
            "from Employee.Dbo.EmployeeMaster e\n" +
            "inner join Shiksha.UDISE.SchoolMaster sm on sm.UDISECode=e.UDISECode\n" +
            "where EmployeeCode=:EmpCode",nativeQuery = true)
    public String getSchoolByEmp(@Param("EmpCode")String  employeeCode);
}