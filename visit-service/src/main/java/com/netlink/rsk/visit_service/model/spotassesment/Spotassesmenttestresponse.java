package com.netlink.rsk.visit_service.model.spotassesment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the Spotassesmenttestresponse database table.
 * 
 */
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Spotassesmenttestresponse",catalog = "Spotass")
@NamedQuery(name="Spotassesmenttestresponse.findAll", query="SELECT s FROM Spotassesmenttestresponse s")
public class Spotassesmenttestresponse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int answer;

	private Long createdby;

	@CreatedDate
	private Date createdon;

	private boolean isresult;

	private int questionid;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="spotassesmentstudentinfoid")
	private Spotassesmentstudentinfo spotassesmentstudentinfo;

	@Column(name="updatedby",nullable = true)

	private Long updatedby;

	@LastModifiedDate
	private Date updatedon;

	//bi-directional one-to-one association to Spotassesmenttestresponse

}