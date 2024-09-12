package com.netlink.rsk.visit_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

/**
 * $table.getTableComment()
 */
@Data
@Entity
@Table(name = "Bankbranchmasterold")
public class BankBranchMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Branchid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long branchId;

    @Column(name = "Bankid", nullable = false)
    private Integer bankId;

    @Column(name = "Branchname", nullable = false)
    private String branchName;

    @Column(name = "Branchaddress", nullable = false)
    private String branchAddress;

    @Column(name = "IFSCcode", nullable = false)
    private String IFSCCode;

    @Column(name = "Isactive", nullable = false)
    private Boolean isActive;

    @Transient
    private BankMaster bankMaster;

}
