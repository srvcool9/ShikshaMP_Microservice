package com.netlink.rsk.visit_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@AllArgsConstructor
@Table(name = "Schoolmaster", catalog = "UDISE")
public class SchoolMaster implements Serializable {

    @Id
    @Column(name = "UDISEcode", nullable = false)
    private String udiseCode;

    @Column(name = "Schoolname", nullable = false)
    private String schoolName;

    @Column(name = "Blockcode", nullable = false)
    private String blockCode;


    @Column(name = "Clustercode", nullable = false)
    private String clusterCode;

    @Column(name = "Categoryid")
    private Integer categoryId;

    @Column(name = "Mgmtid")
    private Integer mgmtId;

    @Column(name = "Locationid")
    private Integer locationId;

    @Column(name = "Typeid")
    private Integer typeId;

    public SchoolMaster() {
    }
    public SchoolMaster(String udiseCode) {
        this.udiseCode = udiseCode;
    }
}
