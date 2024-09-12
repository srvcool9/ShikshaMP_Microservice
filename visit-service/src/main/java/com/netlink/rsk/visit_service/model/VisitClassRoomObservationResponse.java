package com.netlink.rsk.visit_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "Vclassroomobservationresponse",catalog = "Visit")
public class VisitClassRoomObservationResponse implements Serializable {

    @Id
    @Column(name = "Responseid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long responseId;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "Activityid")
    private VisitClassRoomObservationActivity classRoomObservationActivity;

    @OneToOne
    @JoinColumn(name="Parameterid", referencedColumnName = "vparameterid")
    private VparameterMaster parameter;

    @OneToOne
    @JoinColumn(name="Parametervalueid", referencedColumnName = "Vparametervalueid")
    private VparameterValue parameterValue;

    @Column(name = "Createdby")
    private Long createdBy;

    @CreatedDate
    @Column(name = "Createdon")
    private Date createdOn;

    @Column(name = "Updatedby")
    private Integer updatedBy;

    @LastModifiedDate
    @Column(name = "Updatedon")
    private Date updatedOn;
}


