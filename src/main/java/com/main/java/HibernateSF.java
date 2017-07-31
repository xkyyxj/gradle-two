package com.main.java;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateSF {

	StandardServiceRegistry standardRegistry;
	SessionFactory sessionFactory;
	
	public HibernateSF() {
		standardRegistry = new StandardServiceRegistryBuilder()
				.configure( "hibernate.cfg.xml" ).build();
		Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
		sessionFactory = metadata.getSessionFactoryBuilder().build();
	}
	
	public Session getSession() {
		return sessionFactory.openSession();
	}
	
}
