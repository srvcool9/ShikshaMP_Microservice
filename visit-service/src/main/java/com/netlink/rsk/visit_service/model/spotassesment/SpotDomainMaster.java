package com.netlink.rsk.visit_service.model.spotassesment;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.netlink.rsk.visit_service.model.AllClasses;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Domainmaster" ,catalog = "Spotass")
public class SpotDomainMaster implements Serializable {

    @Id
    @Column(name = "Domainid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long domainId;

    @Column(name="Classid")
    private Long classId;

    @Transient
    private AllClasses classMaster;

    @Transient
    private Boolean haveCompetencies;

    @Column(name="Subjectid")
    private Long subjectId;

    @Transient
    private SpotSubjectMaster subjectMaster;

    @Column(name="Domainname")
    private String  domainName;

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

    @OneToMany(mappedBy = "spotDomainMaster",cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "spotDomainMaster")
    private List<CompetencyMaster> competencyMasterList;


}


