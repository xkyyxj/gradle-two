package com.firstapp.main

import org.springframework.beans.factory.annotation.Autowired

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;

import com.firstapp.controller.SpringBeanController
import com.firstapp.entity.User
import com.firstapp.service.UserInterface
import com.firstapp.service.ErrorInfo

@RestController
open class Server {
	
	val springBeanController = SpringBeanController()
	
	@Autowired
	lateinit var userOperation : UserInterface
	
/*	@RequestMapping("/greeting")
	open fun greeting(@RequestParam(value="name", defaultValue="World") name : String) : Greeting
		= Greeting(count.incrementAndGet(),"world! $name")*/
	
	//µÇÂ¼
	@RequestMapping("/login", method = arrayOf(RequestMethod.GET,RequestMethod.POST))
	open fun login(@RequestBody loginUser : User) : ErrorInfo {
		//var returnUser = repository.query(loginUser) as User
		return userOperation.login(loginUser)
	}
	
	//×¢²á
	@RequestMapping("/signup", method = arrayOf(RequestMethod.GET,RequestMethod.POST), produces = arrayOf("application/json; charset=UTF-8"))
	open fun signup (@RequestBody signUpUser : User) : ErrorInfo {
		return userOperation.signUp(signUpUser)
		//returnJSON.put("userID",)
		
	}
	
	@RequestMapping("/insertconsumeinfo", method = arrayOf(RequestMethod.GET,RequestMethod.POST))
	open fun insertConsumeInfo(consumeInfo : String) : String {
		//var returnUser = repository.query(loginUser) as User
		return ""
	}

}