package com.netlink.rsk.visit_service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "Quatermaster", catalog = "Dbo")
public class QuaterMaster implements Serializable {

    @Id
    @Column(name = "Quaterid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quaterId;

    @Column(name = "Quatername")
    private String quaterName;

    @OneToMany(mappedBy = "quater")
    @JsonIgnoreProperties(value ="quater")
    private List<QuaterMonthMapping> quaterMonthMappingList;

}
