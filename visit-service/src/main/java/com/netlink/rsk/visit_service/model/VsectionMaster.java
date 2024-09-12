package com.netlink.rsk.visit_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the VsectionMaster database table.
 * 
 */
@Entity
@Table(name = "Vsectionmaster",catalog = "Visit")
@EntityListeners(AuditingEntityListener.class)
@NamedQuery(name="VsectionMaster.findAll", query="SELECT v FROM VsectionMaster v")
@Cacheable
@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VsectionMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long vsectionid;

	private Long createdby;

	@CreatedDate
	private Date createdon;

	private String description;

	private boolean isactive;

	private String name;

	private Long updatedby;

	@LastModifiedDate
	private Date updatedon;

	//bi-directional many-to-one association to VvisitTypeMaster
	@ManyToOne
	@JoinColumn(name="vvisittypeid")
	//@JsonManagedReference(value = "VtypeMaster")
	private VvisitTypeMaster vvisittypemaster;

	//bi-directional many-to-one association to VsubSectionMaster
	@OneToMany( mappedBy= "vsectionmaster")
//	@JsonManagedReference(value = "subSection")
//	@OneToMany(cascade=CascadeType.ALL, mappedBy="company", orphanRemoval=true)
	//@LazyCollection(LazyCollectionOption.FALSE)
//	@JoinColumn(name="Roleid", referencedColumnName = "Roleid" , insertable = false, updatable = false)
    @JsonIgnore
	private List<VsubSectionMaster> vsubsectionmasters;

	private Boolean isdeleted;

	public Long getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(Long totalRows) {
		this.totalRows = totalRows;
	}

	@Transient
	private Long totalRows;


	public VsectionMaster() {
	}

	public Long getVsectionid() {
		return vsectionid;
	}

	public void setVsectionid(Long vsectionid) {
		this.vsectionid = vsectionid;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public VvisitTypeMaster getVvisittypemaster() {
		return vvisittypemaster;
	}

	public void setVvisittypemaster(VvisitTypeMaster vvisittypemaster) {
		this.vvisittypemaster = vvisittypemaster;
	}

	@JsonIgnore
	public List<VsubSectionMaster> getVsubsectionmasters() {
		return vsubsectionmasters;
	}

	public void setVsubsectionmasters(List<VsubSectionMaster> vsubsectionmasters) {
		this.vsubsectionmasters = vsubsectionmasters;
	}

	public Boolean getIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}
}