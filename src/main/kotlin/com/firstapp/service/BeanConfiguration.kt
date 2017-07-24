package com.firstapp.service

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = arrayOf("com.firstapp.hibernatedao","com.firstapp.serviceimpl"))
open class BeanConfiguration {
	
	/*@Bean
	open fun HibernateMySQLSessonFactory() = HibernateMySQLSessionFactory()
	
	@Bean
	open fun MySQLSession() = Persistence(HibernateMySQLSessonFactory())*/
	
}