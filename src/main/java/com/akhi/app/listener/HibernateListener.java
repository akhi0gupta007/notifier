/**
 * 
 */
package com.akhi.app.listener;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import com.akhi.app.cdm.FiledTask;
import com.akhi.app.cdm.Location;
import com.akhi.app.cdm.Tasks;
import com.akhi.app.cdm.UserDetails;

/**
 * @author akhilesh
 *
 */
public class HibernateListener implements ServletContextListener
    {
    private SessionFactory     factory;
   
    private static Logger      log      = Logger.getLogger(HibernateListener.class);

    public static final String KEY_NAME = "factory";

    @Override
    public void contextDestroyed( ServletContextEvent event )
	{
	// TODO Auto-generated method stub

	}

    @SuppressWarnings("deprecation")
    @Override
    public void contextInitialized( ServletContextEvent event )
	{
    	Session session = null;
    	UserDetails user = new UserDetails();
		user.setUserId("akhi123");
		user.setFirstName("Akhilesh");
		user.setLastName("Gupta");
		user.setPassword("password");
		
		Tasks task = new Tasks();
		task.setConsequence("Fine Of rs 10000");
		task.setGracePeriod(10);
		task.setPeriodicity(2);
		task.setTaskName("DEPOT-P.TAX");
		task.setTaskDescription("PAYMENT OF PROFESSIONAL TAX(BIHAR PROFESSIONAL TAX ACT 2011)");
		
		Location location = new Location();		
		location.setLocationName("Patna");
		
		Collection<Tasks> taskList = new ArrayList<Tasks>();
		taskList.add(task);
		
		location.setTaskList(taskList);
		task.setLocationId(location);
		user.setLocation(location);
		FiledTask file = new FiledTask();
		file.setTask(task);
		file.setUser(user);
    	
    	try
    	{
    	factory = new Configuration().configure().buildSessionFactory();
    	session = factory.openSession();
    	session.beginTransaction();
    	
    	session.save(location);
    	session.save(user);
    	
    	
    	session.save(file);
    	session.getTransaction().commit();
    	event.getServletContext().setAttribute(KEY_NAME, factory);
    	}
    	catch (Exception ex)
    	{
    	session.getTransaction().rollback();
    	log.error(ex.getMessage());
    	}
    	finally
    	{
    	session.close();
    	}
	}



    /*	 try { 
    	 log.info("Context intialised::::::::::::::::::::::::::::::::::::::::::::::::");
    	 
    	        URL url = HibernateListener.class.getResource(path);
    	        config = new Configuration().configure(url);
    	        factory = config.buildSessionFactory();

    	        //save the Hibernate session factory into serlvet context
    	        event.getServletContext().setAttribute(KEY_NAME, factory);
    	  } catch (Exception e) {
    	         System.out.println(e.getMessage());
    	   }
    	
    	}*/

    }
