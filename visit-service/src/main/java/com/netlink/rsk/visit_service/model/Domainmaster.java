package com.netlink.rsk.visit_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the Domainmaster database table.
 * 
 */
@Entity
@Table(name="Domainmaster",catalog = "Visit")
@NamedQuery(name="Domainmaster.findAll", query="SELECT d FROM Domainmaster d")
@EntityListeners(AuditingEntityListener.class)
@Cacheable
@Data
@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_ONLY)

public class Domainmaster implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int domainid;

	private int createdby;

	private Date createdon;

	private String name;

	private int updatedby;

	private Date updatedon;


	private int subsectionid;
	//bi-directional many-to-one association to VsubSectionMaster
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="subsectionid" , referencedColumnName = "vsubsectionid" , insertable = false, updatable = false)
	private VsubSectionMaster vsubSectionMaster;

	@Transient
	Boolean classRoomObservationResponseExists;

	@Transient
	Long activityId;

	@Transient
	private Boolean activityStatus;

	public Domainmaster() {
	}

	public int getDomainid() {
		return domainid;
	}

	public void setDomainid(int domainid) {
		this.domainid = domainid;
	}

	public int getCreatedby() {
		return createdby;
	}

	public void setCreatedby(int createdby) {
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

	public int getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(int updatedby) {
		this.updatedby = updatedby;
	}

	public Date getUpdatedon() {
		return updatedon;
	}

	public void setUpdatedon(Date updatedon) {
		this.updatedon = updatedon;
	}

	public int getSubsectionid() {
		return subsectionid;
	}

	public void setSubsectionid(int subsectionid) {
		this.subsectionid = subsectionid;
	}

	//	//@JsonIgnore
	public VsubSectionMaster getVsubSectionMaster() {
		return vsubSectionMaster;
	}

	public void setVsubSectionMaster(VsubSectionMaster vsubSectionMaster) {
		this.vsubSectionMaster = vsubSectionMaster;
	}
}