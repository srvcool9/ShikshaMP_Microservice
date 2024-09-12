package com.netlink.rsk.visit_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

/**
 * $table.getTableComment()
 */
@Data
@Entity
@Table(name = "Districtmaster",catalog = "UDISE")
public class DistrictMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Districtid", nullable = false)
    private String districtId;

    @Column(name = "Divisionid", nullable = false)
    private Long divisionId;

    @Column(name = "Districtname", nullable = false)
    private String districtName;

    @Column(name = "Districtnameh", nullable = false)
    private String districtNameH;

    @Column(name = "Isactive", nullable = false)
    private Boolean isActive;

}
