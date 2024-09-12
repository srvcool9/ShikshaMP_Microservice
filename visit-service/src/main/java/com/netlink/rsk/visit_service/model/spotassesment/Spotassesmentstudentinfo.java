package com.netlink.rsk.visit_service.model.spotassesment;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.netlink.rsk.visit_service.studentdb.models.StudentDetails;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Spotassesmentstudentinfo database table.
 * 
 */
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Spotassesmentstudentinfo",catalog = "Spotass")
@NamedQuery(name="Spotassesmentstudentinfo.findAll", query="SELECT s FROM Spotassesmentstudentinfo s")
public class Spotassesmentstudentinfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long spotassesmentstudentinfoid;

	private Long createdby;

	@CreatedDate
	private Date createdon;

	private double samagraid;

//	@OneToOne
//	@JoinColumn(name="samagraid", referencedColumnName = "samagra")
//	private StudentDetailAll studentDetailAll;

	@Transient
	private StudentDetails studentDetailAll;


	@OneToOne
	@JoinColumn(name="spotassessmentinfoid", referencedColumnName = "spotassessmentinfoid")
	private Spotassesmentinfo spotassessmentinfo;



	@OneToMany(mappedBy = "spotassesmentstudentinfo")
	//@JsonIgnoreProperties(value = "spotassessmentinfo")
	@JsonManagedReference
	private List<Spotassesmenttestresponse> spotassesmenttestresponse;

	@Column(name="updatedby",nullable = true)
	private Long updatedby;

	@LastModifiedDate
	private Date updatedon;

	private Date starttime;

	private Date endtime;
	//bi-directional one-to-one association to Spotassesmentstudentinfo

}