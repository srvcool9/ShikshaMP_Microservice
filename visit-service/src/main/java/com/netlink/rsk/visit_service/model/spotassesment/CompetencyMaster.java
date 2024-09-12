package com.netlink.rsk.visit_service.model.spotassesment;

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
@Table(name = "Competencymaster" ,catalog = "Spotass")
public class CompetencyMaster implements Serializable {

    @Id
    @Column(name = "Competencymaster", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long competencyMaster;

    @ManyToOne
    @JoinColumn(name = "Domainid", nullable = false)
    private SpotDomainMaster spotDomainMaster;


    @Column(name = "Competencyname")
    private String competencyName;

    @Column(name = "Isactive")
    private Boolean isActive;

    @Column(name = "Createdby")
    private Integer createdBy;

    @CreatedDate
    @Column(name = "Createdon")
    private Date createdOn;

    @Column(name = "Updatedby")
    private Integer updatedBy;

    @LastModifiedDate
    @Column(name = "Updatedon")
    private Date updatedOn;

}
