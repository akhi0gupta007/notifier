/**
 * 
 */
package com.akhi.app.cdm;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author akhilesh
 *
 */
@Entity
@Table(name = "tasks")
public class Tasks {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long taskId;
    @Column(nullable=false)
	private String taskName;
    @Column(nullable=true)
    @Lob
	private String taskDescription;
    @Column(nullable=false)
	private int periodicity;
    @Column(nullable=false)
	private int gracePeriod;
    @Column(nullable=true)
	private String consequence;
    @ManyToOne 
    @JoinColumn(name="location_id",nullable=false)
    private Location locationId;
    
    @OneToMany(mappedBy="task")
    Collection<FiledTask> filedTasks = new ArrayList<FiledTask>();
	
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskDescription() {
		return taskDescription;
	}
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	public int getPeriodicity() {
		return periodicity;
	}
	public void setPeriodicity(int periodicity) {
		this.periodicity = periodicity;
	}
	public int getGracePeriod() {
		return gracePeriod;
	}
	public void setGracePeriod(int gracePeriod) {
		this.gracePeriod = gracePeriod;
	}
	public String getConsequence() {
		return consequence;
	}
	public void setConsequence(String consequence) {
		this.consequence = consequence;
	}
	public Location getLocationId() {
		return locationId;
	}
	public void setLocationId(Location locationId) {
		this.locationId = locationId;
	}
	
	
}
