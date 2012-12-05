package com.akhi.app.action;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.akhi.app.cdm.UserDetails;
import com.opensymphony.xwork2.ActionSupport;

public class WelcomeUserAction extends ActionSupport implements SessionAware
    {

    /**
     * 
     */
    private static final long              serialVersionUID = 7564735574539146404L;

    private static org.apache.log4j.Logger log              = Logger.getLogger(WelcomeUserAction.class);

    private String                         username;

    private String                         password;

    private String                         test;

    Map<String, Object>                    session;

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
	UserDetails user = null;
	SessionFactory sessionFactory = null;
	String result = "FAILURE";

	ServletContext context = ServletActionContext.getServletContext();
	log.debug("Servlet Context :::::::::::: " + context);

	sessionFactory = (SessionFactory) context.getAttribute("factory");
	log.debug("Session Factory Initialised:::::::::::: " + sessionFactory);
	Session session = sessionFactory.openSession();
	session.beginTransaction();

	Query query = session.createQuery("from UserDetails where userId = :user");
	query.setParameter("user", this.getUsername());

	if (query != null && query.list() != null && query.list().size() > 0)
	    {
	    log.info("List :::::::::::::::::::" + query.list());

	    List<UserDetails> list = query.list();
	    user = list.get(0);

	    if (user.getPassword().equals(this.getPassword()))
		{
		result = "SUCCESS";
		}
	    else
		{
		result = "FAILURE";
		addActionError("I don't know you, dont try to hack me!");
		}

	    }
	else
	    {
	    addActionError("I don't know you, dont try to hack me!");
	    }

	if (result.equals("SUCCESS"))
	    {
	    log.info("User Authenticated :::::::::::::::::::");
	    this.getSession().put("user", user);

	    }

	session.close();
	return result;

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

    /**
     * @return the session
     */
    public Map<String, Object> getSession()
	{
	return session;
	}

    @Override
    public void setSession( Map<String, Object> session )
	{
	this.session = session;
	}
    }
