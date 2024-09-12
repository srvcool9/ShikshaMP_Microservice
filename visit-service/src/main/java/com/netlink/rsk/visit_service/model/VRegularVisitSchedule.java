package com.netlink.rsk.visit_service.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the VRegularVisitSchedule database table.
 * 
 */
@Entity
@Table(name="VRegularVisitSchedule", catalog = "Visit")
@EntityListeners(AuditingEntityListener.class)
@NamedQuery(name="VRegularVisitSchedule.findAll", query="SELECT v FROM VRegularVisitSchedule v")
@Cacheable
@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_ONLY)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class VRegularVisitSchedule implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long scheduleid;

	private Long createdby;

	@CreatedDate
	private Date createdon;

	private Date scheduleddate;

	private int schoolallocationid;

	private int status;

	private Long updatedby;

	@LastModifiedDate
	private Date updatedon;

	
	public VRegularVisitSchedule() {
	}

	public Long getScheduleid() {
		return scheduleid;
	}

	public void setScheduleid(Long scheduleid) {
		this.scheduleid = scheduleid;
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

	public Date getScheduleddate() {
		return scheduleddate;
	}

	public void setScheduleddate(Date scheduleddate) {
		this.scheduleddate = scheduleddate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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


}