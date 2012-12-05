/**
 * 
 */
package com.akhi.app.cdm;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author akhilesh
 * 
 */
@Entity
@Table(name = "location")
public class Location
    {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long                    locationId;

    @Column(nullable = false)
    private String                  locationName;

    @Lob
    private String                  description;

    @OneToMany(mappedBy = "locationId", cascade = CascadeType.ALL)
    private Collection<Tasks>       taskList = new ArrayList<Tasks>();

    @OneToMany(mappedBy = "location")
    private Collection<UserDetails> users    = new ArrayList<UserDetails>();

    public String getLocationName()
	{
	return locationName;
	}

    public void setLocationName( String locationName )
	{
	this.locationName = locationName;
	}

    public String getDescription()
	{
	return description;
	}

    public void setDescription( String description )
	{
	this.description = description;
	}

    public Collection<Tasks> getTaskList()
	{
	return taskList;
	}

    public void setTaskList( Collection<Tasks> taskList )
	{
	this.taskList = taskList;
	}

    }
