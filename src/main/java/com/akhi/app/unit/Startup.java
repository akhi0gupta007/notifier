package com.akhi.app.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;


import com.akhi.app.cdm.Location;
import com.akhi.app.cdm.Tasks;
import com.akhi.app.cdm.UserDetails;


/**
 * Basic Set of Tests
 * 
 * @author akhilesh
 * 
 */
public class Startup {
	private SessionFactory factory;

	private static Logger log = Logger.getLogger(Startup.class);


	@SuppressWarnings("deprecation")
	@Before
	public void setUp() throws Exception {
		factory = new Configuration().configure().buildSessionFactory();
	}

	@Test
	public void test() {
		Session session = null;
		try {
			session = factory.openSession();
			session.beginTransaction();
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
			assertNotNull(session.save(user));
			assertNotNull(session.save(location));
			//assertNotNull(session.save(task));
			session.getTransaction().commit();
			session.close();
			user = null;
			
			session = factory.openSession();
			session.beginTransaction();
			
			user = (UserDetails) session.get(UserDetails.class,1L);
			log.info("User" + user);
			user.setDescription("He is out of his mind");
			
			assertEquals("UserName should be akhi","akhi123",user.getUserId());
			location = null;
			user.setUserId("akhi");
			
			location = (Location) session.get(Location.class,1L);
			Collection<Tasks> coll = location.getTaskList();
			assertEquals("Size of Collection should be 1 ",1,coll.size());
			session.save(user);
			session.getTransaction().commit();
			session.close();

		} catch (Exception ex) {
			session.getTransaction().rollback();
			log.error(ex.getMessage());
		}

	
	}

/*	@Test
	public void verifyUser() {
		try{
			Session session = factory.openSession();
			session.beginTransaction();
			UserDetails user = (UserDetails) session.get(UserDetails.class,1L);
			log.info("User" + user);
			//log.debug("User Name " + user.getFirstName() + "" + user.getLastName());
			assertEquals("UserName should be akhi","akhi",user.getUserId());
					
			session.close();
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	

	}*/

}
