package com.netlink.rsk.visit_service.model.spotassesment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.netlink.rsk.visit_service.model.SubjectMaster;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the Spotassesmentinfo database table.
 * 
 */
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Spotassesmentinfo",catalog = "Spotass")
@NamedQuery(name="Spotassesmentinfo.findAll", query="SELECT s FROM Spotassesmentinfo s")
public class Spotassesmentinfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long spotassessmentinfoid;

	private int weekid;




	//private int spotassessmentid;

//	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="spotassessmentid", referencedColumnName = "spotassessmentid" )
	@JsonBackReference
	private Spotassesment spotassessment;

	private int classid;

	@OneToOne
	@JoinColumn(name="subjectid", referencedColumnName = "subjectid")
	private SubjectMaster subject;

	//@ManyToOne
	//@JoinColumn(name="spotassessmentid")
	//private Spotassesment spotassesment;

	private Date assesmentdate;

	private int createdby;

	private Date createdon;


	private String status;

	@Column(name="updatedby",nullable = true)

	private Long updatedby;

	private Date updatedon;

	@Column(name = "subjecttype")
	private Long subjectType;

	private Long noofstudent;
}