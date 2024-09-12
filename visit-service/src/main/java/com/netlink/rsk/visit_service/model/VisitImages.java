package com.netlink.rsk.visit_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Visitimages",catalog = "Visit")
@AllArgsConstructor
@NamedQuery(name="VisitImages.findAll", query="SELECT v FROM VisitImages v")
public class VisitImages implements Serializable {

    @Id
    @Column(name = "Imageid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @Column(name = "Imagename")
    private String imageName;

    @Column(name = "Path")
    private String path;

    @Column(name = "Createdby")
    private Long createdBy;

//    @OneToOne
//    @JoinColumn(name="Createdby", referencedColumnName = "Userid")
//    private UserMaster createdBy;

    @CreatedDate
    @Column(name="Createdon")
    private Date createdOn;

    @Column(name = "Updatedby")
    private Long updatedBy;

//
//    @OneToOne
//    @JoinColumn(name="Updatedby", referencedColumnName = "Userid")
//    private UserMaster updatedBy;

    @LastModifiedDate
    @Column(name="Updatedon")
    private Date updatedOn;

    //bi-directional many-to-one association to VvisitInformation
//    @OneToMany(mappedBy="visitImage")
//    @JsonIgnore
//    private List<VvisitInformation> vvisitInformations;

    @OneToMany(mappedBy="visitImage")
    @JsonIgnore
    private List<SchoolInformation> schoolInformations;


    public VisitImages(){

    }

    public VisitImages(Long imageId){
        this.imageId=imageId;

    }


    @JsonIgnore
    public List<SchoolInformation> getVvisitInformations() {
        return schoolInformations;
    }

    public void setVvisitInformations(List<SchoolInformation> schoolInformations) {
        this.schoolInformations = schoolInformations;
    }
}
