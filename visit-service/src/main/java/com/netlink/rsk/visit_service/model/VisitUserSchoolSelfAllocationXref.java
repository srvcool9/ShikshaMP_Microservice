package com.netlink.rsk.visit_service.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.netlink.rsk.visit_service.dto.ClassroomobservationDTO;
import com.netlink.rsk.visit_service.employeedb.models.EmployeeMaster;
import com.netlink.rsk.visit_service.model.spotassesment.Spotassesment;
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
 * The persistent class for the VisitUserSchoolSelfAllocationXref database
 * table.
 * 
 */
@Data
@Entity
@Table(name = "Visituserschoolselfallocationxref", catalog = "Visit")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@EntityListeners(AuditingEntityListener.class)
@NamedQuery(name = "VisitUserSchoolSelfAllocationXref.findAll", query = "SELECT v FROM VisitUserSchoolSelfAllocationXref v")
@Cacheable
@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_ONLY)
public class VisitUserSchoolSelfAllocationXref implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long schoolallocationid;

	private Date allocateddate;

	private int allocatedid;

	private Long createdby;

	@CreatedDate
	private Date createdon;

	private String employeecode;

	private int month;

	private int roleid;
	@Transient
	private Integer visitTimeTaken;
	//// @OneToOne
	// @JoinColumn(name="Roleid", referencedColumnName = "Roleid" , insertable = false, updatable = false)
//	 private RoleMaster role;


	private String status;

	@Transient
	private String datestatus;


	private int typeid;

	private String typename;


	private String udisecode;

	@Transient
	private String action;


	@Transient
	private SchoolMaster schoolMaster;


	private Long updatedby;

	@LastModifiedDate
	private Date updatedon;

	private int year;

	private Boolean isdeleted;

	private Date starttime;
	private Date endtime;

	@Transient
	Long teacherDetailsId;

	@Transient
	Long classroomObservationId;

	@Transient
	Long visitInformationId;

	@Transient
	private ClusterMaster clusterMaster;

	@Transient
	private BlockMaster blockMaster;

	@Transient
	private DistrictMaster districtMaster;

	@Transient
	private DivisionMaster divisionMaster;


	@Transient
	private boolean classroomObservationExists;

	@Transient
	private Long schoolInformationId;

	@Transient
	private ClassroomobservationDTO classroomobservationDTO;

	@Transient
	private List<Spotassesment> spotassesmentList;

	@Transient
	private VfeedbackResponse feedbackResponse;

	@Transient
	private List<EmployeeMaster> employeeList;

	@Transient
	private List<StudentDetails> studentList;

	public VisitUserSchoolSelfAllocationXref(){
	}
	public VisitUserSchoolSelfAllocationXref(Long schoolallocationid){
		this.schoolallocationid=schoolallocationid;
	}



	
	

}