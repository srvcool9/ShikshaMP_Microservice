package com.netlink.rsk.visit_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

/**
 * $table.getTableComment()
 */
@Data
@Entity
@Table(name = "Blockmaster",catalog = "UDISE")
public class BlockMaster implements Serializable {

    @Id
    @Column(name = "Blockid", nullable = false)
    private String blockId;

    @Column(name = "Districtid", nullable = false)
    private String districtId;

    @Column(name = "Blockname", nullable = false)
    private String blockName;

//    @Column(name = "Blocknameh", nullable = false)
//    private String blockNameH;

    @Column(name = "Isheadquater", nullable = false)
    private Boolean isHeadQuater;

//    @Column(name = "Isactive", nullable = false)
//    private Boolean isActive;

    @OneToOne
    @JoinColumn(name="Districtid", referencedColumnName = "Districtid" , insertable = false, updatable = false )
    private DistrictMaster districtMaster;


}
