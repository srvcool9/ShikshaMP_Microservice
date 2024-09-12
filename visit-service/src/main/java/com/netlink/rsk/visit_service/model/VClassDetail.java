package com.netlink.rsk.visit_service.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the VClassDetails database table.
 * 
 */
@Entity
@Table(name="Vclassdetails",catalog = "Visit")
@Data
@EntityListeners(AuditingEntityListener.class)
@NamedQuery(name="VClassDetail.findAll", query="SELECT v FROM VClassDetail v")
@Cacheable
@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_ONLY)
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class VClassDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long classdetailsid;

	private int answerid;

	private int classname;

	private int createdby;

	@CreatedDate
	private Date createdon;

	private int numberofstudents;

	private int schoolallocationid;

	private String teacherid;

	private int updatedby;

	@LastModifiedDate
	private Date updatedon;

	public VClassDetail() {
	}


}