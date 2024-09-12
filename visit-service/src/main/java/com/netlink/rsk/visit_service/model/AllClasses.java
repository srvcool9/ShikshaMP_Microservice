package com.netlink.rsk.visit_service.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Allclasses" ,catalog = "Dbo")
public class AllClasses implements Serializable {

    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Classname")
    private String className;

    @Column(name = "Classnameh")
    private String classNameHindi;

    @Column(name = "Isactive")
    private Boolean isActive;

    @Transient
    private Long totalRows;


}
