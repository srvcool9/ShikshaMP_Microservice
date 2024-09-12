package com.netlink.rsk.visit_service.studentdb.models;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "StudentDetail25" ,catalog = "Dbo")
public class StudentDetails implements Serializable {


    @Id
    @Column(name = "Samagra", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long samagra;


    @Column(name = "UDISECode")
    private String udiseCode;


    @Column(name = "Classid")
    private Integer classId;


    @Column(name = "Name")
    private String name;

    @Column(name = "Fathername")
    private String fatherName;

    @Column(name = "Mothername")
    private String motherName;

    @Column(name = "Dob")
    private Date dob;

    @Transient
    private int age;

    @Column(name = "Gender")
    private String gender;

    @Column(name = "Category")
    private String category;

    @Column(name = "Mobileno")
    private String mobile;

    @Transient
    private Boolean testStatus;

    @Transient
    private Date assessmentDate;

}
