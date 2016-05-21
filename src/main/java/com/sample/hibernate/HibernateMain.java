package com.sample.hibernate;

import java.util.Date;
 
import org.hibernate.Session;

public class HibernateMain {
 
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
        System.out.println("Employee ID="+emp.getId());
         
        //terminate session factory, otherwise program won't end
        HibernateUtil.getSessionFactory().close();
    }
 
}
