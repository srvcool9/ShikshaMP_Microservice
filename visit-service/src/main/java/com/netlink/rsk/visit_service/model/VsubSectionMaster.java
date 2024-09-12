package com.netlink.rsk.visit_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Vsubsectionmaster",catalog = "Visit")
@EntityListeners(AuditingEntityListener.class)
@Data
public class VsubSectionMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long vsubsectionid;

	private Long createdby;

	@CreatedDate
	private Date createdon;

	private String description;

	private boolean isactive;

	private String name;

	private Long updatedby;

	//@Column(name="IsEnableDomain")
	private boolean isenabledomain;

	@LastModifiedDate
	private Date updatedon;

	//bi-directional many-to-one association to VsectionMaster
	@ManyToOne
	@JoinColumn(name="vsectionid")
	//@JsonBackReference(value = "subSection")
	private VsectionMaster vsectionmaster;

	@OneToMany(mappedBy="vsubSectionMaster")
	@JsonIgnore
	private List<VparameterMaster> vparameterMasters;

	//bi-directional many-to-one association to Domainmaster
	@OneToMany(mappedBy="vsubSectionMaster")
	//@JsonIgnore
	private List<Domainmaster> domainMasters;

	@Transient
	private Boolean isFilled;

	@Transient
	private Integer week;

	@Transient
	private Integer day;

	@Transient
	private Long activityId;

	@Transient
	private Boolean activityStatus;

	public Boolean getFilled() {
		return isFilled;
	}

	public void setFilled(Boolean filled) {
		isFilled = filled;
	}

	@JsonIgnore
	public List<VparameterMaster> getVparameterMasters() {
		return vparameterMasters;
	}

}