package com.sample.hibernate;

import java.util.Properties;
 
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateUtil {
 
	static final  Logger logger = LoggerFactory.getLogger(HibernateUtil.class);

    //Property based configuration
    private static SessionFactory sessionJavaConfigFactory;
 
 
 
    private static SessionFactory buildSessionJavaConfigFactory() {
        try {
        Configuration configuration = new Configuration();
         
        //Create Properties, can be read from property files too
        Properties props = new Properties();
        props.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        props.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/employee");
        props.put("hibernate.connection.username", "root");
        props.put("hibernate.connection.password", "naveen");
        props.put("hibernate.current_session_context_class", "thread");
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.format_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "create");
        props.put("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
        props.put("hibernate.cache.use_second_level_cache", "true");
        props.put("hibernate.cache.use_query_cache", "true");
        props.put("net.sf.ehcache.configurationResourceName", "/myehcache.xml");
        
        configuration.setProperties(props);
         
        //we can set mapping file or class with annotation
        //addClass(Employee1.class) will look for resource
        // com/journaldev/hibernate/model/Employee1.hbm.xml (not good)
        configuration.addAnnotatedClass(Employee.class);
        configuration.addAnnotatedClass(Transact.class);
        configuration.addAnnotatedClass(Customer.class);
        configuration.addAnnotatedClass(Cart.class);
        configuration.addAnnotatedClass(Items.class);
        configuration.addAnnotatedClass(Item.class);
        configuration.addAnnotatedClass(Kart.class);
        
         
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        logger.info("Hibernate Java Config serviceRegistry created");
         
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
         
        return sessionFactory;
        }
        catch (Throwable ex) {
        	logger.error("Initial SessionFactory creation failed." + ex,ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionJavaConfigFactory() {
        if(sessionJavaConfigFactory == null){
        	sessionJavaConfigFactory = buildSessionJavaConfigFactory();
        }
        return sessionJavaConfigFactory;
    }
     
}
