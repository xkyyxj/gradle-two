package com.firstapp.hibernatedao

import javax.annotation.PreDestroy

import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.boot.MetadataSources
import org.hibernate.boot.registry.StandardServiceRegistry
import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import org.springframework.util.ResourceUtils

import org.springframework.stereotype.Component

@Component
class HibernateSessionFactory {
	
	init {
		configure()
	}
	
	lateinit var standardRegistry : StandardServiceRegistry
	
	lateinit var sessionFactory : SessionFactory
	
	/**
	 * ����һ��Hibernate��SessionFactory
	 */
	private fun configure(){
		//��hibernate.cfg.xml�ļ��ƶ���src/main/resourcesԴ����£���Ȼgradle���ܽ�������jar������
		var url = ResourceUtils.getURL("classpath:hibernateConfig/hibernate.cfg.xml")
		standardRegistry = StandardServiceRegistryBuilder()
				.configure( url ).build()
		var metadata = MetadataSources(standardRegistry).getMetadataBuilder().build()
		sessionFactory = metadata.getSessionFactoryBuilder().build()
	}
	
	/**
	 * ��ȡһ��Sessionʵ��
	 *
	 * @return ����Sessionʵ��
	 */
	fun getSession() : Session = sessionFactory.openSession()
	
	@PreDestroy
	fun destroy(){
		StandardServiceRegistryBuilder.destroy(standardRegistry)
	}
	
}