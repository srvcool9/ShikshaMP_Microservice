package com.netlink.rsk.visit_service.model.spotassesment;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Subjectmaster" ,catalog = "Spotass")
public class SpotSubjectMaster implements Serializable {

    @Id
    @Column(name = "Subjectid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subjectId;

    @Column(name="Subjectname")
    private String subjectName;

    @Column(name="Isactive")
    private Boolean isActive;

    @Transient
    private long totalRows;
}
