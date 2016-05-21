package com.sample.hibernate;

import java.util.Date;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateMain {
	final static Logger logger = LoggerFactory.getLogger(HibernateMain.class);
    public static void main(String[] args) {
        Employee emp = new Employee();
        emp.setName("naveen");
        emp.setRole("CEO");
        emp.setInsertTime(new Date());
         
        //Get Session
        Session session = HibernateUtil.getSessionJavaConfigFactory().getCurrentSession();
        //start transaction
        session.beginTransaction();
        //Save the Model object
        session.save(emp);
        //Commit transaction
        session.getTransaction().commit();
        logger.info("Employee ID="+emp.getId());
         
        //terminate session factory, otherwise program won't end
        HibernateUtil.getSessionJavaConfigFactory().close();
    }
 
}
