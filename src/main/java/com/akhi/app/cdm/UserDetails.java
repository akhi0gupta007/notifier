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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenerationTime;

/**
 * @author akhilesh
 *
 */
@Entity
public class UserDetails
    {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @Column(name="userId", nullable=false)
    private String userId;
    @Column(nullable=false)
    private String firstName;
    private String lastName;
    @Column(nullable=false)
    private String password;
    
    @Column(name="DATE_CREATED", insertable=false, updatable=false, columnDefinition="timestamp default current_timestamp")
    @org.hibernate.annotations.Generated(value=GenerationTime.INSERT)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Column(name="LAST_MODIFIED", insertable=false, updatable=false, columnDefinition="datetime")
    @org.hibernate.annotations.Generated(value=GenerationTime.ALWAYS)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;
    @ManyToOne 
    @JoinColumn(nullable=true,name="location_id")
    private Location location;
    
    @Lob
    private String description;
    
    /**
     * @return the id
     */
    public long getId()
        {
        return id;
        }
    /**
     * @param id the id to set
     */
    public void setId( long id )
        {
        this.id = id;
        }
    /**
     * @return the userId
     */
    public String getUserId()
        {
        return userId;
        }
    /**
     * @param userId the userId to set
     */
    public void setUserId( String userId )
        {
        this.userId = userId;
        }
    /**
     * @return the firstName
     */
    public String getFirstName()
        {
        return firstName;
        }
    /**
     * @param firstName the firstName to set
     */
    public void setFirstName( String firstName )
        {
        this.firstName = firstName;
        }
    /**
     * @return the lastName
     */
    public String getLastName()
        {
        return lastName;
        }
    /**
     * @param lastName the lastName to set
     */
    public void setLastName( String lastName )
        {
        this.lastName = lastName;
        }
    /**
     * @return the password
     */
    public String getPassword()
        {
        return password;
        }
    /**
     * @param password the password to set
     */
    public void setPassword( String password )
        {
        this.password = password;
        }
    public String getDescription()
	{
	    return description;
	}
    public void setDescription( String description )
	{
	    this.description = description;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
    
    }
