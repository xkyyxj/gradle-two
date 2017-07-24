package com.firstapp.serviceimpl

import java.util.Date

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import com.firstapp.service.UserInterface
import com.firstapp.entity.User
import com.firstapp.dao.Repository

@Component
class UserService : UserInterface{
	
	@Autowired
	lateinit var repository : Repository
	
	override fun signUp(signUpUser : User) : Int {
		var currentTime = Date()
		signUpUser.signuptime = currentTime
		var operationStatus = repository.insert(signUpUser)
		if(operationStatus == Repository.SUCCESS)
			return UserInterface.SUCCESS
		else
			return UserInterface.FAILED
	}
	
	override fun login(loginUser : User) : Int {
		//TODO -- finish login
		return UserInterface.SUCCESS
	}
	
}