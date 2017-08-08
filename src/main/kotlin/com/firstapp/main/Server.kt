package com.firstapp.main

import org.springframework.beans.factory.annotation.Autowired

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;

import com.firstapp.controller.SpringBeanController
import com.firstapp.entity.User
import com.firstapp.entity.ConsumeType
import com.firstapp.service.UserInterface
import com.firstapp.service.MainService
import com.firstapp.service.ErrorInfo
import com.firstapp.service.CommonReturnInfo

@RestController
open class Server {
	
	val springBeanController = SpringBeanController()
	
	@Autowired
	lateinit var userOperation : UserInterface
	
	@Autowired
	lateinit var mainService : MainService
	
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
	
	@RequestMapping("/insertconsumetypeinfo", method = arrayOf(RequestMethod.GET,RequestMethod.POST))
	open fun insertConsumeTypeInfo(@RequestBody consumeTypeInfo : ConsumeType) : CommonReturnInfo {
		//var returnUser = repository.query(loginUser) as User
		return mainService.insertConsumeType(consumeTypeInfo)
	}
	
	@RequestMapping("/requireTypeInfo", method = arrayOf(RequestMethod.GET,RequestMethod.POST))
	open fun requireTypeInfo(@RequestBody map : Map<String, Any>) : Array<ConsumeType>? {
		//var returnUser = repository.query(loginUser) as User
		return mainService.requireTypeInfo((map.get("userId") as Int).toLong())
	}

}