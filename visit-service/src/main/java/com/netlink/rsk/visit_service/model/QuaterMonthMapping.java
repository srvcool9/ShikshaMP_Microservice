package com.netlink.rsk.visit_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "Quatermonthmapping", catalog = "Dbo")
public class QuaterMonthMapping implements Serializable {

    @Id
    @Column(name = "Qmmid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quaterMappingId;

    @ManyToOne
    @JoinColumn(name = "Quaterid")
    private QuaterMaster quater;

    @Column(name = "Monthid")
    private Integer monthId;

    @Column(name = "Monthname")
    private String monthName;
}
