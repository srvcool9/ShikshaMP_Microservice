package com.netlink.rsk.visit_service.model.spotassesment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the WeekRangeMaster database table.
 * 
 */
@Entity
@NamedQuery(name="Weekmaster.findAll", query="SELECT w FROM Weekmaster w")
@Data
@Table(name = "Weekmaster",catalog = "Itembank")
@Cacheable
@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_ONLY)
public class Weekmaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long weekid;

	private int weekendrange;

	private int weekstrrange;

	private boolean isactive;

	//bi-directional many-to-one association to SpotAssmentSMCQ
//	@OneToMany(mappedBy="weekMaster")
//	private List<SpotAssmentSMCQ> spotAssmentSmcqs;

	public Weekmaster() {
	}

	@OneToMany(mappedBy = "weekMaster",cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value = "weekMaster")
	private List<WeekDetail> weekDetailList;

	@Transient
	private long totalRows;


}