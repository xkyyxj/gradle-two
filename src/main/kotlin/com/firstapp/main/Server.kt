package com.firstapp.main

import org.springframework.beans.factory.annotation.Autowired

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.concurrent.atomic.AtomicLong

import com.firstapp.controller.SpringBeanController
import com.firstapp.data.User
import com.firstapp.dataaccess.Repository

@RestController
open class Server {
	
	val count : AtomicLong = AtomicLong()
	
	val springBeanController = SpringBeanController()
	
	@Autowired
	lateinit var repository : Repository
	
	@RequestMapping("/greeting")
	open fun greeting(@RequestParam(value="name", defaultValue="World") name : String) : Greeting
		= Greeting(count.incrementAndGet(),"world! $name")
	
	//µÇÂ¼
	@RequestMapping("/login", method = arrayOf(RequestMethod.GET,RequestMethod.POST))
	open fun login(@RequestBody loginUser : User) {
		//var returnUser = repository.query(loginUser) as User
		
	}
	
	//×¢²á
	@RequestMapping("/signup", method = arrayOf(RequestMethod.GET,RequestMethod.POST))
	open fun signup (@RequestBody signUpUser : User) {
		repository.insert(signUpUser)
	}

}