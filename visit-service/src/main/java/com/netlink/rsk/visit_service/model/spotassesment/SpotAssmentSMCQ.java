package com.netlink.rsk.visit_service.model.spotassesment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.netlink.rsk.visit_service.model.LOMaster;
import com.netlink.rsk.visit_service.model.SubjectMaster;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the SpotAssmentSMCQ database table.
 * 
 */
@Entity
@Data
@Table(name = "SpotAssmentSMCQ",catalog = "Itembank")
@NamedQuery(name="SpotAssmentSMCQ.findAll", query="SELECT s FROM SpotAssmentSMCQ s")
@Cacheable
@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_ONLY)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class SpotAssmentSMCQ implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int SMCQId;

	@OneToOne
	@JoinColumn(name="Subjectid", referencedColumnName = "Subjectid")
	private SubjectMaster subject;

//	@Column(name = "ChapterId")
	private Long chapterid;

	@OneToOne
	@JoinColumn(name="Loid", referencedColumnName = "Loid")
	private LOMaster lo;

	@Column(name="Answer")
	private int answer;

//	@Column(name="Createdon")
	private Date Createdon;

	private int DLId;

	@Column(name="IPAddress")
	private String IPAddress;


	@Column(name="OptionA")
	private String optionA;

	@Column(name="OptionB")
	private String optionB;

	@Column(name="OptionC")
	private String optionC;

	@Column(name="OptionD")
	private String optionD;

	private String QImage;

	@Column(name="Question")
	private String question;

	@Column(name="Reasoninga")
	private String reasoninga;

	@Column(name="Reasoningb")
	private String reasoningb;

	@Column(name="Reasoningc")
	private String reasoningc;

	@Column(name="Reasoningd")
	private String reasoningd;

	@Column(name="Skillid")
	private int skillId;

	@Column(name="Statusid")
	private int statusid;

	@ManyToOne
	@JoinColumn(name="weekid")
	private Weekmaster weekMaster;



}