package com.netlink.rsk.visit_service.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the VfeedbackParameterMaster database table.
 * 
 */
@Entity
@Table(name="Vfeedbackparametermaster",catalog = "Visit")
@EntityListeners(AuditingEntityListener.class)
@NamedQuery(name="VfeedbackParameterMaster.findAll", query="SELECT v FROM VfeedbackParameterMaster v")
@Cacheable
@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_ONLY)
@Data
public class VfeedbackParameterMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long vfeedbackmasterid;

	private int createdby;

	@CreatedDate
	private Date createdon;

	private String parameter;

	private String parametervalue1;

	private String parametervalue2;

	private String parametervalue3;

	@LastModifiedDate
	private Long updatedby;

	private Date updatedon;

	@Transient
	private Integer validHours;

	public VfeedbackParameterMaster() {
	}

}