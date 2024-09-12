package com.netlink.rsk.visit_service.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the SchoolInformation database table.
 * 
 */

@Entity
@Table(name="Schoolinformation",catalog = "Visit")
@Data
@NamedQuery(name="SchoolInformation.findAll", query="SELECT s FROM SchoolInformation s")
@EntityListeners(AuditingEntityListener.class)
@Cacheable
@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_ONLY)
//@JsonInclude(JsonInclude.Include.NON_NULL)

public class SchoolInformation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long schoolinformationid;

	private int answer;

	private int createdby;

	@CreatedDate
	private Date createdon;

	private String employeeid;

	private int numberofstudents;

	private String longitude;

	private String latitude;

	private Date startime;

	private int status;

	private String teacherid;

	private String mentorid;

	private int updatedby;

	@LastModifiedDate
	private Date updatedon;

	private String visityype;

	//bi-directional many-to-one association to Classmaster
	@ManyToOne
	@JoinColumn(name="classid")
	private Classmaster classMaster;


	//bi-directional many-to-one association to VisitImage
	@ManyToOne
	@JoinColumn(name="imageid")
	private VisitImages visitImage;

	//bi-directional many-to-one association to VisitUserSchoolSelfAllocationXref
	@ManyToOne
	@JoinColumn(name="schoolallocationid")
	private VisitUserSchoolSelfAllocationXref visitUserSchoolSelfAllocationXref;

	public SchoolInformation() {
	}

}