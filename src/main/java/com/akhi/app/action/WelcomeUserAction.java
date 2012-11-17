package com.akhi.app.action;

import org.apache.log4j.Logger;
import javax.servlet.ServletContext;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.akhi.app.cdm.UserDetails;
import javax.servlet.http.HttpServletRequest;

public class WelcomeUserAction
    {

    private static org.apache.log4j.Logger log = Logger.getLogger(WelcomeUserAction.class);

    private String                         username;
    
    private String password;
    
    private String test;
    

    public String getUsername()
	{
	return username;
	}

    public void setUsername( String username )
	{
	this.username = username;
	}


    public String execute()
	{
	SessionFactory sessionFactory = null;
	HttpServletRequest request = ServletActionContext.getRequest();
	request.setAttribute("my","akhilesh");
	log.info("Hibernate Session Factory initialised ");
	ServletContext context = ServletActionContext.getServletContext();
	log.debug("Servlet Context :::::::::::: "+ context);
	sessionFactory = (SessionFactory) context.getAttribute("factory");
	log.debug("Session Factory Initialised:::::::::::: "+ sessionFactory);
	Session session = sessionFactory.openSession();
	session.beginTransaction();
	UserDetails user = (UserDetails) session.get(UserDetails.class,1L);
	log.debug("User Name " + user.getFirstName() + "" + user.getLastName());
	this.setTest("akhilesh");
	
	session.close();
	return "SUCCESS";

	}

    public String getPassword()
	{
	    return password;
	}

    public void setPassword( String password )
	{
	    this.password = password;
	}

    public String getTest()
	{
	    return test;
	}

    public void setTest( String test )
	{
	    this.test = test;
	}
    }
