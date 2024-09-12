package com.netlink.rsk.visit_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "Lomaster",catalog = "Itembank")
public class LOMaster implements Serializable {

    @Id
    @Column(name = "Loid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loId;

    @Column(name="Locode")
    private String loCode;

    @Column(name="Loname")
    private String loName;

    @Column(name="Isactive")
    private Boolean isActive;


}
