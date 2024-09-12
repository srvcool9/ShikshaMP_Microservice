package com.netlink.rsk.visit_service.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the VparameterValues database table.
 * 
 */
@Entity
@Table(name="Vparametervalues",catalog = "Visit")
@EntityListeners(AuditingEntityListener.class)
@NamedQuery(name="VparameterValue.findAll", query="SELECT v FROM VparameterValue v")
@Cacheable
@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_ONLY)
public class VparameterValue implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long vparametervalueid;

	private String actionprompts;

	private BigDecimal weightage;

	private Boolean gr1;

	private Long createdby;

	@CreatedDate
	private Date createdon;

	private String description;

	private int orderno;

	private Long updatedby;

	@LastModifiedDate
	private Date updatedon;

	//bi-directional many-to-one association to VparameterMaster
	@ManyToOne
	@JoinColumn(name="vparameterid")
	@JsonBackReference(value = "vparametervalues")
	private VparameterMaster vparameterMaster;

	public VparameterValue() {
	}

	public Long getVparametervalueid() {
		return vparametervalueid;
	}

	public void setVparametervalueid(Long vparametervalueid) {
		this.vparametervalueid = vparametervalueid;
	}

	public String getActionprompts() {
		return actionprompts;
	}

	public void setActionprompts(String actionprompts) {
		this.actionprompts = actionprompts;
	}

	public BigDecimal getWeightage(){ return weightage;}

	public void setWeightage(BigDecimal weightage){this.weightage = weightage;}

	public Boolean getGr1(){ return gr1;}

	public void setGr1(Boolean gr1){
		this.gr1 = gr1;
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

	public int getOrderno() {
		return orderno;
	}

	public void setOrderno(int orderno) {
		this.orderno = orderno;
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

	public VparameterMaster getVparameterMaster() {
		return vparameterMaster;
	}

	public void setVparameterMaster(VparameterMaster vparameterMaster) {
		this.vparameterMaster = vparameterMaster;
	}

	
}