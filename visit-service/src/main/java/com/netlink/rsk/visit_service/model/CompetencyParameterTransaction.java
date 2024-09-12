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
@Table(name = "Competencyparametertransaction",catalog = "Visit")
public class CompetencyParameterTransaction implements Serializable {

    @Id
    @Column(name = "Transactionid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(name="Competencyid")
    private Long competencyId;

    @Column(name = "Sectionid")
    private Long sectionId;

    @Column(name = "Vparameterid")
    private Long vparameterId;

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

}
