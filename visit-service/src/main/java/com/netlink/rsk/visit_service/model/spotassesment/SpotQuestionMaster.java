package com.netlink.rsk.visit_service.model.spotassesment;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Questionmaster" ,catalog = "Spotass")
public class SpotQuestionMaster implements Serializable {

    @Id
    @Column(name = "Questionid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @Column(name="Classid")
    private Long classId;

    @Column(name="Subjectid")
    private Long subjectId;

    @OneToOne
    @JoinColumn(name="Weekrangeid", referencedColumnName = "weekid" )
    private Weekmaster weekRange;

    @OneToOne
    @JoinColumn(name="Domainid", referencedColumnName = "Domainid" )
    private SpotDomainMaster domainMaster;


    @OneToOne
    @JoinColumn(name="Competencyid", referencedColumnName = "Competencymaster" )
    private CompetencyMaster competencyMaster;

    @Column(name = "Imagename")
    private String imageName;

   @Column(name = "Imagepath")
    private String imagePath;

    @Column(name = "Questionname")
    private String questionName;

    @Transient
    private String question;

    @Column(name = "Optiona")
    private String optionA;

    @Column(name = "Optionb")
    private String optionB;

    @Column(name = "Optionc")
    private String optionC;

    @Column(name = "Isactive")
    private Boolean isActive;

    @Column(name = "Createdby")
    private Long createdBy;

    @CreatedDate
    @Column(name = "Createdat")
    private Date createdAt;

    @Transient
    private byte[] images;

    @Transient
    private String base64Image;



}
