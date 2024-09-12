package com.netlink.rsk.visit_service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Visitclassroomobservationactivity",catalog = "Visit")
public class VisitClassRoomObservationActivity implements Serializable {

    @Id
    @Column(name = "Activityid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long activityId;

    @OneToOne
    @JoinColumn(name="Subsectionid", referencedColumnName = "vsubsectionid")
    private VsubSectionMaster subSection;

    @OneToOne
    @JoinColumn(name="Domainid", referencedColumnName = "domainid")
    private Domainmaster domain;

    @OneToOne
    @JoinColumn(name="Subjectid", referencedColumnName = "vsectionid")
    private VsectionMaster sectionMaster;

    @Column(name="Week")
    private Integer week;

    @Column(name="Day")
    private Integer day;

    @Column(name="Activitystatus")
    private Boolean activityStatus;
    @ManyToOne
    @JoinColumn(name = "Classroomobservationid")
    private VisitClassRoomObservation classRoomObservation;

    @JsonManagedReference
    @OneToMany(mappedBy = "classRoomObservationActivity")
    @JsonIgnoreProperties(value = "classRoomObservationActivity")
    private List<VisitClassRoomObservationResponse> responseList;

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
