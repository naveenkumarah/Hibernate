package com.sample.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateMain {
	static final  Logger logger = LoggerFactory.getLogger(HibernateMain.class);
	public static void simpleHibernateExample(SessionFactory sessionFactory){
		Employee emp = new Employee();
        emp.setName("naveen");
        emp.setRole("CEO");
        emp.setInsertTime(new Date());
         
        //Get Session
        Session session = sessionFactory.getCurrentSession();
        //start transaction
        session.beginTransaction();
        //Save the Model object
        session.save(emp);
        //Commit transaction
        session.getTransaction().commit();
        logger.info("Employee ID="+emp.getId());
         
	}
	public static void oneToOneExample(SessionFactory sessionFactory){
		Transact txn = buildDemoTransaction();
        
        Session session = null;
        Transaction tx = null;
        try{
        session = sessionFactory.getCurrentSession();
        logger.info("Session created using annotations configuration");
        //start transaction
        tx = session.beginTransaction();
        //Save the Model object
        session.save(txn);
        //Commit transaction
        tx.commit();
        logger.info("Annotation Example. Transaction ID="+txn.getId());
         
        //Get Saved Trasaction Data
        printTransactionData(txn.getId(), sessionFactory);
        }catch(Exception e){
        	logger.error("Exception occured. "+e.getMessage(),e);
        }
	}
	
	private static void printTransactionData(long id, SessionFactory sessionFactory) {
        Session session = null;
        Transaction tx = null;
        try{
            session = sessionFactory.getCurrentSession();
            //start transaction
            tx = session.beginTransaction();
            //Save the Model object
            Transact txn = (Transact) session.get(Transact.class, id);
            //Commit transaction
            tx.commit();
            logger.info("Annotation Example. Transaction Details=\n"+txn);
             
            }catch(Exception e){
            	logger.error("Exception occured. "+e.getMessage(),e);
            }
    }
 
    private static Transact buildDemoTransaction() {
    	Transact txn = new Transact();
        txn.setDate(new Date());
        txn.setTotal(100);
         
        Customer cust = new Customer();
        cust.setAddress("Bangalore");
        cust.setEmail("test@yahoo.com");
        cust.setName("Customer Name");
         
        txn.setCustomer(cust);
         
        cust.setTxn(txn);
        return txn;
    }
    public static void oneToManyExample(SessionFactory sessionFactory){
    	Cart cart = new Cart();
        cart.setName("MyCart1");
         
        Items item1 = new Items("I10", 10, 1, cart);
        Items item2 = new Items("I20", 20, 2, cart);
        Set<Items> itemsSet = new HashSet<Items>();
        itemsSet.add(item1); itemsSet.add(item2);
         
        cart.setItems(itemsSet);
        cart.setTotal(10*1 + 20*2);
         
        Session session = null;
        Transaction tx = null;
        try{
        session = sessionFactory.getCurrentSession();
        logger.info("Session created");
        //start transaction
        tx = session.beginTransaction();
        //Save the Model object
        session.save(cart);
        session.save(item1);
        session.save(item2);
        //Commit transaction
        tx.commit();
        logger.info("Cart1 ID="+cart.getId());
        logger.info("item1 ID="+item1.getId()+", Foreign Key Cart ID="+item1.getCart().getId());
        logger.info("item2 ID="+item2.getId()+", Foreign Key Cart ID="+item1.getCart().getId());
         
        }catch(Exception e){
        	logger.error("Exception occured. "+e.getMessage(),e);
        }
    }
    public static void manyToManyExample(SessionFactory sessionFactory){
    	Item item1 = new Item();
        item1.setDescription("samsung"); 
        item1.setPrice(300);
        Item item2 = new Item();
        item2.setDescription("nokia"); 
        item2.setPrice(200);
        Kart kart = new Kart();
        kart.setTotal(500);
        Set<Item> items = new HashSet<Item>();
        items.add(item1); 
        items.add(item2);
        kart.setItems(items);
         
        try{
       
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        session.save(kart);
        logger.info("Before committing transaction");
        tx.commit();
        sessionFactory.close();
         
        logger.info("Kart ID="+kart.getId());
        logger.info("Item1 ID="+item1.getId());
        logger.info("Item2 ID="+item2.getId());
         
        }catch(Exception e){
        	logger.error("Exception occured. "+e.getMessage(),e);
        }
    }
    public static void main(String[] args) {
    	SessionFactory sessionFactory= null;
    	try {
    		sessionFactory= HibernateUtil.getSessionJavaConfigFactory();
    		simpleHibernateExample(sessionFactory);
        	oneToOneExample(sessionFactory);
        	oneToManyExample(sessionFactory);
        	manyToManyExample(sessionFactory);
		} catch (Exception e) {
			logger.error("Exception occured. "+e.getMessage(),e);
		}finally{
            if(!sessionFactory.isClosed()){
            	logger.info("Closing SessionFactory");
                sessionFactory.close();
            }
        }
    	
    }
 
}
