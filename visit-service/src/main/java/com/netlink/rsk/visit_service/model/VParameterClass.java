package com.netlink.rsk.visit_service.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Vparameterclass" ,catalog = "Visit")
public class VParameterClass implements Serializable {

    @Id
    @Column(name = "Parameterclassid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parameterClassId;

    @OneToOne
    @JoinColumn(name="Classid", referencedColumnName = "Id")
    private AllClasses classes;

    @ManyToOne
    @JoinColumn(name="Vparameterid")
    private VparameterMaster vparameterMaster;

    @Column(name = "Createdby")
    private Long createdBy;

    @CreatedDate
    @Column(name = "Createdon")
    private Date createdOn;

    @Column(name = "Updatedby")
    private Long updatedBy;

    @LastModifiedDate
    @Column(name = "Updatedon")
    private Date updatedOn;


}
