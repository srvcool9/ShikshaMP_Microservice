package com.netlink.rsk.visit_service.model.spotassesment;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.netlink.rsk.visit_service.model.SchoolMaster;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Spotassesment database table.
 * 
 */
@Entity
@Data
@Table(name = "Spotassesment",catalog = "Spotass")
@EntityListeners(AuditingEntityListener.class)
@NamedQuery(name="Spotassesment.findAll", query="SELECT s FROM Spotassesment s")
public class Spotassesment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long spotassessmentid;

	@Temporal(TemporalType.DATE)
	private Date assesmentdate;

	private Long createdby;

	@CreatedDate
	private Date createdon;

//	private Long learningoutcomeid;



	private String employeecode;


	private int roleid;



	private String status;

	//private String UDISECode;
	@OneToOne
	@JoinColumn(name = "UDISECode", referencedColumnName = "UDISEcode")
	private SchoolMaster UDISECode;

	@Column(name="updatedby",nullable = true)
	private Long updatedby;

	@LastModifiedDate
	@Column(name="updatedon",nullable = true)
	private Date updatedon;

	private Long schoolallocationid;

	private Boolean isweb;

	@Transient
	long totalStudents;

	@JsonManagedReference
	@OneToMany(mappedBy="spotassessment")
	private List<Spotassesmentinfo> spotassesmentinfo;



	public Spotassesment(){
	}

	public Spotassesment(Long spotassessmentid){
		this.spotassessmentid=spotassessmentid;
	}

}