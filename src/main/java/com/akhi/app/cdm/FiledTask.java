/**
 * 
 */
package com.akhi.app.cdm;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenerationTime;

/**
 * @author akhilesh
 * 
 */
@Entity
public class FiledTask {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long fileId;
	
	@ManyToOne
	@JoinColumn(nullable = false, name = "taskId")
	private Tasks task;
	
	private String attachment;
	private String remarks;
	private String noAttachment;
	
	@ManyToOne
	@JoinColumn(nullable = false, name = "userId")
	private UserDetails user;

	@Column(name = "DATE_CREATED", insertable = false, updatable = false, columnDefinition = "timestamp default current_timestamp")
	@org.hibernate.annotations.Generated(value = GenerationTime.INSERT)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;

	public Tasks getTask() {
		return task;
	}

	public void setTask(Tasks task) {
		this.task = task;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getNoAttachment() {
		return noAttachment;
	}

	public void setNoAttachment(String noAttachment) {
		this.noAttachment = noAttachment;
	}

	public UserDetails getUser() {
		return user;
	}

	public void setUser(UserDetails user) {
		this.user = user;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	

}
