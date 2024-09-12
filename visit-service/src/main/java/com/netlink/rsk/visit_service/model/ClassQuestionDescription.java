package com.netlink.rsk.visit_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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


/**
 * The persistent class for the ClassQuestionDescription database table.
 *
 */
@Entity
@Table(name="Classquestiondescription",catalog = "Visit")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name="ClassQuestionDescription.findAll", query="SELECT c FROM ClassQuestionDescription c")
@EntityListeners(AuditingEntityListener.class)
@Cacheable
@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_ONLY)

public class ClassQuestionDescription implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long descriptionid;

	private int createdby;

	@CreatedDate
	private Date createdon;

	private String description;

	//private int order;

	private int updatedby;

	@LastModifiedDate
	private Date updatedon;

	//private Long classid;

	//bi-directional many-to-one association to Classmaster
	//@JsonIgnore
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="classid" )
	private Classmaster classmaster;



}