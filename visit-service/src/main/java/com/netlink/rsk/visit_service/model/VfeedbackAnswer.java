package com.netlink.rsk.visit_service.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;



/**
 * The persistent class for the VfeedbackAnswers database table.
 * 
 */
@Entity
@Table(name="Vfeedbackanswers",catalog = "Visit")
@EntityListeners(AuditingEntityListener.class)
@NamedQuery(name="VfeedbackAnswer.findAll", query="SELECT v FROM VfeedbackAnswer v")
@Cacheable
@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_ONLY)
@Data
public class VfeedbackAnswer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long vfeedbackanswersid;

	private Long createdby;

	@CreatedDate
	private Date createdon;

	private int updatedby;

	@LastModifiedDate
	private Date updatedon;

	private int vfeedbackanswer;

	private int vfeedbackmasterid;

	//bi-directional many-to-one association to VfeedbackResponse
	@ManyToOne
	@JoinColumn(name="vfeedbackresponseid")
	private VfeedbackResponse vfeedbackResponse;

	public VfeedbackAnswer() {
	}

}