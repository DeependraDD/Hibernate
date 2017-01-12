package com.hbt.conf;



import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtils {
	
	private static  SessionFactory factory;
	public static SessionFactory getObject(){
		
		if(factory==null){
			
			factory= new AnnotationConfiguration().configure().buildSessionFactory();
		}
		
		return factory;
	}
	
	

}
	