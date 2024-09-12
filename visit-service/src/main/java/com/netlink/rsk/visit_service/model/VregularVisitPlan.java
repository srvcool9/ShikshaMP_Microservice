package com.netlink.rsk.visit_service.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the VregularVisitPlan database table.
 * 
 */
@Data
@Entity
@Table(name = "Vregularvisitplan",catalog = "Visit")
@EntityListeners(AuditingEntityListener.class)
@NamedQuery(name="VregularVisitPlan.findAll", query="SELECT v FROM VregularVisitPlan v")
@Cacheable
@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_ONLY)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VregularVisitPlan implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long vregularvisitid;

	private Long createdby;

	@CreatedDate
	private Date createdon;

	private boolean isactive;

	private int roleid;
	@OneToOne
	@JoinColumn(name="Roleid", referencedColumnName = "Roleid" , insertable = false, updatable = false)
	@LazyCollection(LazyCollectionOption.FALSE)
	private RoleMaster role;


	private boolean selfallocation;

	private Long updatedby;

	@LastModifiedDate
	private Date updatedon;

	private int visitpermonth;

	private Integer month;
	private Integer year;

	public VregularVisitPlan() {
	}

	public Long getVregularvisitid() {
		return vregularvisitid;
	}

	public void setVregularvisitid(Long vregularvisitid) {
		this.vregularvisitid = vregularvisitid;
	}

	public Long getCreatedby() {
		return createdby;
	}

	public void setCreatedby(Long createdby) {
		this.createdby = createdby;
	}

	public Date getCreatedon() {
		return createdon;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public boolean isSelfallocation() {
		return selfallocation;
	}

	public void setSelfallocation(boolean selfallocation) {
		this.selfallocation = selfallocation;
	}

	public Long getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(Long updatedby) {
		this.updatedby = updatedby;
	}

	public Date getUpdatedon() {
		return updatedon;
	}

	public void setUpdatedon(Date updatedon) {
		this.updatedon = updatedon;
	}

	public int getVisitpermonth() {
		return visitpermonth;
	}

	public void setVisitpermonth(int visitpermonth) {
		this.visitpermonth = visitpermonth;
	}


}