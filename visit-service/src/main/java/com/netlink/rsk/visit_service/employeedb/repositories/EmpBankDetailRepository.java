package com.netlink.rsk.visit_service.employeedb.repositories;


import com.netlink.rsk.visit_service.employeedb.models.EmployeeBankDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpBankDetailRepository extends JpaRepository<EmployeeBankDetail,String>, JpaSpecificationExecutor<EmployeeBankDetail> {
    EmployeeBankDetail findByEmployeeCode(String employeeCode);
}
