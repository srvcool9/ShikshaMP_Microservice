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
@Table(name = "Mentoringweightagefeedback",catalog = "Visit")
public class MentoringWeightageFeedback implements Serializable {

    @Id
    @Column(name = "Feedbackid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedbackId;

    @Column(name="Scorerangefrom")
    private Double scoreRangeFrom;

    @Column(name="Scorerangeto")
    private Double scoreRangeTo;

    @Column(name = "Feedback")
    private String feedback;

    @Column(name = "Competencyid")
    private Long competencyId;

    @Column(name = "Color")
    private String color;

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
