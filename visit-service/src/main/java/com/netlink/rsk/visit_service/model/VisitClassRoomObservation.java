package com.netlink.rsk.visit_service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "Vclassroomobservation",catalog = "Visit")
public class VisitClassRoomObservation implements Serializable {

    @Id
    @Column(name = "Classroomobservationid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classRoomObservationId;

    @ManyToOne
    @JoinColumn(name = "Schoolallocationid")
    private VisitUserSchoolSelfAllocationXref schoolAllocation;

    @OneToMany(mappedBy = "classRoomObservation")
    @JsonIgnoreProperties(value = "classRoomObservation")
    private List<VisitClassRoomObservationActivity> classRoomObservationActivityList;

    @Column(name = "Status")
    private String status;

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
