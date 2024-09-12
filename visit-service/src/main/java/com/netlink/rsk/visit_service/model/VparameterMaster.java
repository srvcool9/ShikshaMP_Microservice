package com.netlink.rsk.visit_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.netlink.rsk.visit_service.dto.ClassroomObservationResponseDTO;
import com.netlink.rsk.visit_service.dto.ParameterWeekMappingDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the VparameterMaster database table.
 * 
 */
@Entity
@Table(name = "Vparametermaster",catalog = "Visit" )
@EntityListeners(AuditingEntityListener.class)
@Data
public class VparameterMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	   
	private Long vparameterid;

	private Long createdby;

	@CreatedDate
	private Date createdon;

	private String gradegroupid;

	private boolean isactive;

	private boolean mandatory;

	private Long ordernumber;



	private String parametername;

	private String purposeid;

	private String quaterid;

	private String parametertype;
	//private String typeid;

	@ManyToOne
	@JoinColumn(name="typeid")
	private VvisitTypeMaster vvisitTypeMaster;


	private Long updatedby;

	@LastModifiedDate
	private Date updatedon;

	private String visitorroleid;

	private int vsubsectionid;

	private Integer domainmasterid;

	@OneToOne
	@JoinColumn(name="domainmasterid", referencedColumnName = "domainid",insertable = false,updatable = false)
	private Domainmaster domainmaster;

	@JsonIgnore
    @ManyToOne
  //  @JoinColumn(name="vsubsectionid"   insert="false" update="false")
	@JoinColumn(name="vsubsectionid", referencedColumnName = "vsubsectionid" , insertable = false, updatable = false)
    private VsubSectionMaster vsubSectionMaster;

	//bi-directional many-to-one association to VparameterMaster
//	@OneToMany(mappedBy="vsubSectionMaster")
//	@JsonIgnore
//	private List<VparameterMaster> vparameterMasters;


	//bi-directional many-to-one association to VparameterValue
	@OneToMany(mappedBy="vparameterMaster")
	@JsonManagedReference(value = "vparametervalues")
	private List<VparameterValue> vparameterValues;


	@OneToMany(mappedBy="vparameterMaster")
	@JsonIgnoreProperties(value = "vparameterMaster")
	private List<VParameterClass> vParameterClasses;

	@OneToMany(mappedBy="vparameterMaster")
	@JsonIgnoreProperties(value = "vparameterMaster")
	private List<VParameterWeekMapping> vParameterWeekMappings;

	@Transient
	private List<ParameterWeekMappingDTO> weekMapping;


	@Transient
	List<EntityMaster> purpose;

	@Transient
	List<QuaterMaster> quaterMaster;

	@Transient
	List<RoleMaster> visitorRole;

	@Transient
	List<GradeGroups> gradeGroup;

	@Transient
	ClassroomObservationResponseDTO classroomObservationResponse;

	@Transient
	List<AllClasses> classes;

}