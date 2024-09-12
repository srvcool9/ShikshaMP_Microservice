package com.netlink.rsk.visit_service.employeedb.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "Employeebankdetail" ,catalog = "dbo")
public class EmployeeBankDetail implements Serializable {

    @Id
    @Column(name = "Employeecode",nullable = false)
    private String employeeCode;

    @Column(name = "Ifsccode")
    private String ifscCode;

    @Column(name = "Bankaccountno")
    private String bankAccountNo;

    @Column(name = "Accountholdername")
    private String accountHolderName;

    @Column(name = "Created_By")
    private String createdBy;

    @CreatedDate
    @Column(name = "Created_At")
    private Date createdOn;

}
