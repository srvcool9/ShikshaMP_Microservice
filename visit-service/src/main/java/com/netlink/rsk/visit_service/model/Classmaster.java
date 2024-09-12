package com.netlink.rsk.visit_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Classmaster database table.
 *
 */


@Entity
@Table(name="Classmaster",catalog = "Visit")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name="Classmaster.findAll", query="SELECT c FROM Classmaster c")
@EntityListeners(AuditingEntityListener.class)
@Cacheable
@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_ONLY)
public class Classmaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long classid;

	private int createdby;

	@CreatedDate
	private Date createdon;

	private String question;

	private int updatedby;

	@LastModifiedDate
	private Date updatedon;

	private boolean isactive;


	@OneToMany(mappedBy="classmaster")
	private List<ClassQuestionDescription> classQuestionDescriptions;
//	@OneToMany(targetEntity = ClassQuestionDescription.class,cascade=CascadeType.ALL)
//	@JoinColumn(name="classid" ,referencedColumnName = "classid")
//	private List<ClassQuestionDescription> classQuestionDescriptions;


	private String classname;

	@Transient
	List<ClassMasterTransaction> classMasterTransactions;

	public Classmaster(Long classid){
		this.classid=classid;

	}


}