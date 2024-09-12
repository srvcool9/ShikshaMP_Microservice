package com.netlink.rsk.visit_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Mentoringdomainmaster",catalog = "Visit")
public class MentoringDomainMaster implements Serializable {

    @Id
    @Column(name = "Domainid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long domainId;

    @Column(name="Domainname")
    private String domainName;

    @Column(name = "VSectionid")
    private Long vsectionId;

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

    @Transient
    private String sectionName;



}
