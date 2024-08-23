package com.netlink.rsk.visit_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.io.Serializable;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name="Vregularvisitplan", catalog = "Visit")
public class VRegularVisitSchedule implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vregularvisitid")
	private Long regularVisitId;

	@Column(name = "roleid")
	private Long roleId;

	@Column(name = "visitpermonth")
	private Integer visitPerMonth;

	@Column(name = "selfallocation")
	private Boolean selfAllocation;

	@Column(name = "isactive")
	private Boolean isActive;

	@Column(name = "month")
	private Integer month;

	@Column(name = "year")
	private Integer year;

	@Transient
	private List<?> list;
}