package com.firstapp.hibernatedao

import javax.annotation.PreDestroy

import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.boot.MetadataSources
import org.hibernate.boot.registry.StandardServiceRegistry
import org.hibernate.boot.registry.StandardServiceRegistryBuilder

import org.springframework.stereotype.Component

@Component
class HibernateSessionFactory {
	
	init {
		configure()
	}
	
	lateinit var standardRegistry : StandardServiceRegistry
	
	lateinit var sessionFactory : SessionFactory
	
	private fun configure(){
		standardRegistry = StandardServiceRegistryBuilder()
				.configure( "hibernate.cfg.xml" ).build()
		var metadata = MetadataSources(standardRegistry).getMetadataBuilder().build()
		sessionFactory = metadata.getSessionFactoryBuilder().build()
	}
	
	fun getSession() : Session = sessionFactory.openSession()
	
	@PreDestroy
	fun destroy(){
		StandardServiceRegistryBuilder.destroy(standardRegistry)
	}
	
}