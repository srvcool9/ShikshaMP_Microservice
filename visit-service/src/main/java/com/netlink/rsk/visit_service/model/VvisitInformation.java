package com.netlink.rsk.visit_service.model;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the VvisitInformations database table.
 * 
 */

@Data
@Entity
@Table(name="Vvisitinformations", catalog = "Visit")
@EntityListeners(AuditingEntityListener.class)
@NamedQuery(name="VvisitInformation.findAll", query="SELECT v FROM VvisitInformation v")
@Cacheable
@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_ONLY)
public class VvisitInformation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long visitinformationid;

	private int createdby;

	@CreatedDate
	private Date createdon;

	private String employeeid;

	private Date startime;

	private int status;

	private Long updatedby;

	@LastModifiedDate
	private Date updatedon;

	private int visitid;

	private String visityype;

	private String longitutde;

	private String latitude;

	//bi-directional many-to-one association to VisitImage
	@ManyToOne
	@JoinColumn(name="imageid")
	private VisitImages visitImage;

	//bi-directional many-to-one association to VisitUserSchoolSelfAllocationXref
	@ManyToOne
	@JoinColumn(name="schoolallocationid")
	private VisitUserSchoolSelfAllocationXref visitUserSchoolSelfAllocationXref;

	//bi-directional one-to-one association to VvisitInformation

	public VvisitInformation() {
	}
	public VvisitInformation(Long visitinformationid) {
		this.visitinformationid=visitinformationid;
	}



}