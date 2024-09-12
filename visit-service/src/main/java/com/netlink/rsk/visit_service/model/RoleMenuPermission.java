package com.netlink.rsk.visit_service.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Rolemenupermission", catalog = "Dbo")
public class RoleMenuPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Rolemenupermissionid", nullable = false)
    private Long roleMenuPermissionId;

    @ManyToOne
    @JoinColumn(name = "Roleid")
    private RoleMaster roleMaster;

    @Column(name = "Roletypeid", nullable = false)
    private Long roleTypeId;

    @OneToOne
    @JoinColumn(name = "Menuid", referencedColumnName = "MenuiD")
    private MenuMaster menu;

    @Column(name = "[View]")
    private Boolean view;

    @Column(name = "Edit", nullable = false)
    private Boolean edit;

    @Column(name = "[Add]" , nullable = false)
    private Boolean add;

    @Column(name = "[Delete]" , nullable = false)
    private Boolean delete;

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

}
