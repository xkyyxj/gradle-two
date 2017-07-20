package com.firstapp.controller

import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class SpringBeanController {
	
	val applicationContext : ApplicationContext
	
	init{
		applicationContext = AnnotationConfigApplicationContext(com.firstapp.controller.BeanConfiguration::class.java)
	}
	
	fun getBean(beanName : String) = applicationContext.getBean(beanName)
	
}