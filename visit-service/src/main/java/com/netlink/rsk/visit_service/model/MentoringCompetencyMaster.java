package com.netlink.rsk.visit_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Mentoringcompetencymaster",catalog = "Visit")
public class MentoringCompetencyMaster implements Serializable {

    @Id
    @Column(name = "Competencyid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long competencyId;

    @Column(name="Competencyname")
    private String competencyName;

    @Column(name = "Domainid")
    private Long domainId;

    @Transient
    private String domainName;

    @Transient
    private String sectionName;

    @Transient
    private Long sectionId;

    @Column(name="Isactive")
    private Boolean isActive;

    @Column(name="Createdby")
    private Long createdBy ;

    @CreatedDate
    @Column(name="Createdon")
    private Date createdOn ;

    @Column(name="Updatedby")
    private Long updatedBy ;

    @LastModifiedDate
    @Column(name="Updatedon")
    private Date updatedOn ;

    @Column(name = "Orderno")
    private Long orderNo;
}
