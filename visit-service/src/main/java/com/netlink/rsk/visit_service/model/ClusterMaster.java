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
@Table(name = "Clustermaster",catalog = "UDISE")
public class ClusterMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Clustercode", nullable = false)
    private String clusterCode;

    @Column(name = "Blockcode", nullable = false)
    private String blockCode;

    @Column(name = "UDISEcode")
    private String UDISECode;

    @Column(name = "Clustername", nullable = false)
    private String clusterName;

    @Column(name = "Isactive", nullable = false)
    private Boolean isActive;

}
