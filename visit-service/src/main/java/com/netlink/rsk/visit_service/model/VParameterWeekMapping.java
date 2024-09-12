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
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Vparameterweekmapping" ,catalog = "Visit")
public class VParameterWeekMapping implements Serializable {

    @Id
    @Column(name = "Parameterweekid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parameterWeekId;

    @Column(name = "Weekrangeid")
    private Long weekRangeId;


    @Column(name = "Vparameterid")
    private Long vparameterMaster;

//    @ManyToOne
//    @JoinColumn(name="Vparameterid")
//    private VparameterMaster vparameterMaster;

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
