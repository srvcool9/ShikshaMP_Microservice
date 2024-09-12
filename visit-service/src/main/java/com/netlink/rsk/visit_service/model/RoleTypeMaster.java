package com.netlink.rsk.visit_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

/**
 * $table.getTableComment()
 */
@Data
@Entity
@Table(name = "Roletypemaster",catalog = "Dbo")
public class RoleTypeMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Roletypeid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleTypeID;

    @Column(name = "Roletypename", nullable = false)
    private String roleTypeName;

    @Column(name = "Roletypeorder")
    private Integer roleTypeOrder;

}
