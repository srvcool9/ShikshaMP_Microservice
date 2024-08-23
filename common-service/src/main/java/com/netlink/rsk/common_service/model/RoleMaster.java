package com.netlink.rsk.common_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Rolemaster",schema = "Dbo")
public class RoleMaster implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "Roleid",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleID;

//    @OneToOne
//    @JoinColumn(name = "Roletypeid", referencedColumnName = "Roletypeid")
//    private RoleTypeMaster roleTypeId;

    @Column(name = "Rolename", nullable = false)
    private String roleName;

    @Column(name = "Roledescription")
    private String roleDescription;

    @Column(name = "Isactive")
    private Boolean isActive;

    @Column(name = "Hierarchyorder")
    private Integer hierarchyOrder;


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


}
