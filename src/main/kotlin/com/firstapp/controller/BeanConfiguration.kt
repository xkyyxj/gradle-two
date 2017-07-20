package com.firstapp.controller

import org.springframework.context.annotation.*;
import com.firstapp.data.HibernateSessionFactory

@Configuration
@ComponentScan(basePackages = arrayOf("com.firstapp.hibernatedata"))
open class BeanConfiguration {
	
	/*@Bean
	open fun HibernateMySQLSessonFactory() = HibernateMySQLSessionFactory()
	
	@Bean
	open fun MySQLSession() = Persistence(HibernateMySQLSessonFactory())*/
	
}