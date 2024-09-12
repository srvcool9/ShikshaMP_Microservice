package com.netlink.rsk.visit_service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Subjectmaster",catalog = "SpotAss")
public class SubjectMaster implements Serializable {

    @Id
    @Column(name = "Subjectid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subjectId;

    @Column(name="Subjectname")
    private String subjectName;

    @Column(name="Isactive")
    private Boolean isActive;


   public SubjectMaster(Long subjectId){
      this.subjectId=  subjectId;
    }


}
