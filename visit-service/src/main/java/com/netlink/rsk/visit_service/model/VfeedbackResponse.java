package com.netlink.rsk.visit_service.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the VfeedbackResponse database table.
 * 
 */
@Entity
@Table(name="Vfeedbackresponse",catalog = "Visit")
@EntityListeners(AuditingEntityListener.class)
@NamedQuery(name="VfeedbackResponse.findAll", query="SELECT v FROM VfeedbackResponse v")
@Data
public class VfeedbackResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long vfeedbackresponseid;

	private Long createdby;

	@CreatedDate
	private Date createdon;

	private String empcode;

	private String feedback;

	private int updatedby;

	@LastModifiedDate
	private Date updatedon;

	private int vschoolallocationid;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private String status;

	//bi-directional many-to-one association to VfeedbackAnswer
	@JsonIgnore
	@OneToMany(mappedBy="vfeedbackResponse",fetch = FetchType.EAGER)
	private List<VfeedbackAnswer> vfeedbackAnswers;

	public VfeedbackResponse() {
	}

	public Long getVfeedbackresponseid() {
		return vfeedbackresponseid;
	}

	public void setVfeedbackresponseid(Long vfeedbackresponseid) {
		this.vfeedbackresponseid = vfeedbackresponseid;
	}

	public Long getCreatedby() {
		return createdby;
	}

	public void setCreatedby(Long createdby) {
		this.createdby = createdby;
	}

	public Date getCreatedon() {
		return createdon;
	}

	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}

	public String getEmpcode() {
		return empcode;
	}

	public void setEmpcode(String empcode) {
		this.empcode = empcode;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public int getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(int updatedby) {
		this.updatedby = updatedby;
	}

	public Date getUpdatedon() {
		return updatedon;
	}

	public void setUpdatedon(Date updatedon) {
		this.updatedon = updatedon;
	}

	public int getVschoolallocationid() {
		return vschoolallocationid;
	}

	public void setVschoolallocationid(int vschoolallocationid) {
		this.vschoolallocationid = vschoolallocationid;
	}

	public List<VfeedbackAnswer> getVfeedbackAnswers() {
		return vfeedbackAnswers;
	}

	public void setVfeedbackAnswers(List<VfeedbackAnswer> vfeedbackAnswers) {
		this.vfeedbackAnswers = vfeedbackAnswers;
	}
}