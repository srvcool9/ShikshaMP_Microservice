package com.netlink.rsk.visit_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "Bankmasterold")
public class BankMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Bankid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bankId;

    @Column(name = "Bankname", nullable = false)
    private String bankName;

    @Column(name = "Isactive", nullable = false)
    private Boolean isActive;

}
