package com.netlink.rsk.visit_service.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the VClassroomObservation database table.
 *
 */
@Entity
@Data
@Table(name="Vclassroomobservation",catalog = "Visit")
@EntityListeners(AuditingEntityListener.class)
@NamedQuery(name="VClassroomObservation.findAll", query="SELECT v FROM VClassroomObservation v")
@Cacheable
@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_ONLY)
public class VClassroomObservation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long classroomobservationid;

	private int answerid;

	private int createdby;

	@CreatedDate
	private Date createdon;

	private String day;

	private String imageid;

	private int parameterid;

	private String remark;

	private int schoolallocationid;

	private int sectionid;

	private int subsectionid;

	private int updatedby;

	@LastModifiedDate
	private Date updatedon;

	private int week;

	@Transient
	List<VisitImages> visitImagesList;

	public VClassroomObservation() {
	}



}