package com.netlink.rsk.visit_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * $table.getTableComment()
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Entitymaster",catalog = "Dbo")
public class EntityMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Entityid", nullable = false)
    private Integer entityId;

    @Column(name = "Entitytypeid", nullable = false)
    private Long entityTypeId;

    @Column(name = "Entityname", nullable = false)
    private String entityName;

    @Column(name = "Isactive", nullable = false)
    private Boolean isActive;

    @Transient
    @Column(name = "name")
    private String name;

}
