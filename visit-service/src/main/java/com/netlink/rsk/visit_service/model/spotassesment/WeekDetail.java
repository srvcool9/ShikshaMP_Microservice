package com.netlink.rsk.visit_service.model.spotassesment;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Weekdetail" ,catalog = "Spotass")
public class WeekDetail implements Serializable {

    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Weekid", nullable = false)
    private Weekmaster weekMaster;

    @Column(name = "Weekname")
    private String weekName;

    @Column(name = "Isactive")
    private Boolean isActive;


}
