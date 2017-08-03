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
	 * 构建一个Hibernate的SessionFactory
	 */
	private fun configure(){
		//将hibernate.cfg.xml文件移动到src/main/resources源码包下，不然gradle不能将其打包到jar包当中
		var url = ResourceUtils.getURL("classpath:hibernateConfig/hibernate.cfg.xml")
		standardRegistry = StandardServiceRegistryBuilder()
				.configure( url ).build()
		var metadata = MetadataSources(standardRegistry).getMetadataBuilder().build()
		sessionFactory = metadata.getSessionFactoryBuilder().build()
	}
	
	/**
	 * 获取一个Session实例
	 *
	 * @return 返回Session实例
	 */
	fun getSession() : Session = sessionFactory.openSession()
	
	@PreDestroy
	fun destroy(){
		StandardServiceRegistryBuilder.destroy(standardRegistry)
	}
	
}