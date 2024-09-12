package com.netlink.rsk.visit_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the VvisitTypeMaster database table.
 * 
 */
@Entity
@Table(name = "Vvisittypemaster",catalog = "Visit")
@EntityListeners(AuditingEntityListener.class)
@NamedQuery(name="VvisitTypeMaster.findAll", query="SELECT v FROM VvisitTypeMaster v")
@Cacheable
@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_ONLY)
public class VvisitTypeMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long vvisittypemasterid;

	private Long createdby;

	@CreatedDate
	private Date createdon;

	private boolean isactive;

	private String name;

	private BigDecimal scoringpercentage;

	@LastModifiedDate
	private Long updatedby;

	private Date updatedon;

	//bi-directional many-to-one association to VsectionMaster
	@OneToMany(mappedBy="vvisittypemaster")
//	@JsonIgnoreProperties(value = "vsectionmasters")
//	@JsonBackReference(value = "VtypeMaster")
	@JsonIgnore
	private List<VsectionMaster> vsectionmasters;

	//bi-directional many-to-one association to VTypeMaster
	@ManyToOne
	@JoinColumn(name="vtypemasterid")
//	@JsonManagedReference(value = "VtypeMaster")
//	@JsonBackReference(value = "VtypeMaster")

	private VTypeMaster vtypemaster;

	public VvisitTypeMaster() {
	}

	public Long getVvisittypemasterid() {
		return vvisittypemasterid;
	}

	public void setVvisittypemasterid(Long vvisittypemasterid) {
		this.vvisittypemasterid = vvisittypemasterid;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getScoringpercentage() {
		return scoringpercentage;
	}

	public void setScoringpercentage(BigDecimal scoringpercentage) {
		this.scoringpercentage = scoringpercentage;
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

	@JsonIgnore
	public List<VsectionMaster> getVsectionmasters() {
		return vsectionmasters;
	}

	public void setVsectionmasters(List<VsectionMaster> vsectionmasters) {
		this.vsectionmasters = vsectionmasters;
	}

	public VTypeMaster getVtypemaster() {
		return vtypemaster;
	}

	public void setVtypemaster(VTypeMaster vtypemaster) {
		this.vtypemaster = vtypemaster;
	}
}