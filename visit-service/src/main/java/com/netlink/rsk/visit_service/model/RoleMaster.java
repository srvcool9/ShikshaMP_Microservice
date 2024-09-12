package com.netlink.rsk.visit_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * $table.getTableComment()
 */
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Rolemaster",catalog = "Dbo")
public class RoleMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Roleid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleID;

    @OneToOne
    @JoinColumn(name = "Roletypeid", referencedColumnName = "Roletypeid")
    private RoleTypeMaster roleTypeId;

    @Column(name = "Rolename", nullable = false)
    private String roleName;

    @Column(name = "Roledescription")
    private String roleDescription;

    @Column(name = "Isactive")
    private Boolean isActive;

    @Column(name = "Hierarchyorder")
    private Integer hierarchyOrder;

    @OneToMany(mappedBy = "roleMaster")
    @JsonIgnoreProperties(value = "roleMaster")
    @JsonBackReference(value = "roleMaster")
    private List<RoleMenuPermission> roleMenuPermissionList;

    @Column(name = "Createdby")
    private Long createdBy;

    @CreatedDate
    @Column(name = "Createdon")
    private Date createdOn;

    @Column(name = "Updatedby")
    private Long updatedBy;

    @LastModifiedDate
    @Column(name = "Updatedon")
    private Date updatedOn;

    @Transient
    private Long roleId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getRoleID() {
        return roleID;
    }

    public void setRoleID(Long roleID) {
        this.roleID = roleID;
    }
}
