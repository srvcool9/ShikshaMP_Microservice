package com.netlink.rsk.visit_service.employeedb.models;

import com.netlink.rsk.visit_service.model.BankBranchMaster;
import com.netlink.rsk.visit_service.model.SchoolMaster;
import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@Table(name = "Employeemaster" ,catalog = "dbo")
public class EmployeeMaster implements Serializable {


    @Id
    @Column(name = "Id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Employeecode", nullable = false)
    private String employeeCode;


    @Column(name = "UDISEcode", nullable = false)
    private String UDISECode;

    @Column(name = "Employeename", nullable = false)
    private String employeeName;

    @Column(name = "Designation", nullable = false)
    private String designation;

    @Column(name = "Gender")
    private String gender;

    @Column(name = "Subjectexpert")
    private String subjectExpert;

    @Column(name = "DOB")
    private Date DOB;

    @Column(name = "DOJ")
    private Date DOJ;

    @Column(name = "Mobileno")
    private String mobileNo;

    @Column(name = "Emailid")
    private String email;

    @Column(name = "District")
    private String  districtName;

    @Column(name = "Isincharge")
    private Boolean isInCharge;

    @Column(name = "Isretired")
    private Boolean isRetired;

    @Transient
    private SchoolMaster schoolMaster;

    @Transient
    private BankBranchMaster bankBranchMaster;

}
