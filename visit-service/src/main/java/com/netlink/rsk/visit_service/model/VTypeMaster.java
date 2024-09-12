package com.netlink.rsk.visit_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the VTypeMaster database table.
 * 
 */
@Entity
@Table(name = "Vtypemaster",catalog = "Visit")
@EntityListeners(AuditingEntityListener.class)
@NamedQuery(name="VTypeMaster.findAll", query="SELECT v FROM VTypeMaster v")
@Cacheable
@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_ONLY)
public class VTypeMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long vtypemasterid;

	private Long createdby;

	@CreatedDate
	private Date createdon;

	private String name;

	private Long updatedby;

	@LastModifiedDate
	private Date updatedon;

	//bi-directional many-to-one association to VvisitTypeMaster
	@OneToMany(mappedBy="vtypemaster")
	@JsonBackReference(value = "VtypeMaster")
	private List<VvisitTypeMaster> vvisittypemasters;

	public VTypeMaster() {
	}

	public Long getVtypemasterid() {
		return vtypemasterid;
	}

	public void setVtypemasterid(Long vtypemasterid) {
		this.vtypemasterid = vtypemasterid;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<VvisitTypeMaster> getVvisittypemasters() {
		return vvisittypemasters;
	}

	public void setVvisittypemasters(List<VvisitTypeMaster> vvisittypemasters) {
		this.vvisittypemasters = vvisittypemasters;
	}
}