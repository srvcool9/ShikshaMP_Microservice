package com.netlink.rsk.visit_service.model.spotassesment;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "Questionpicker" ,catalog = "Spotass")
public class QuestionPicker  implements Serializable {

    @Id
    @Column(name = "Questionpickerid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionPickerId;

    @Column(name = "Weekid")
    private Long weekId;

    @Column(name = "Competencyid")
    private Long competencyId;


    @Column(name = "Noquestion")
    private Integer noOfQuestions;

    @Transient
    private long totalRows;

}
