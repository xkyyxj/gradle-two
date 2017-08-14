package com.firstapp.serviceimpl

import java.util.Date

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.firstapp.service.UserInterface
import com.firstapp.entity.User
import com.firstapp.dao.Repository
import com.firstapp.service.ErrorInfo

@Component
class UserService : UserInterface{
	
	val logger : Logger = LoggerFactory.getILoggerFactory().getLogger("fileLog")
	
	@Autowired
	lateinit var repository : Repository
	
	override fun signUp(signUpUser : User) : ErrorInfo {
		//查询下该用户名是否已经存在
		var hql = "select user.name from User user where name=?"
		var returnResult = repository.query(hql, signUpUser.name,returnType = String::class)
		if(returnResult == null || returnResult.size == 0){
			var currentTime = Date()
			signUpUser.signuptime = currentTime
			var (operationStatus, _) = repository.insert(signUpUser)
			if(operationStatus == Repository.SUCCESS) {
				logger.info("User(Name : {}) sign up successful", signUpUser.name)
				return ErrorInfo(UserInterface.SUCCESS,"SUCCESS",signUpUser.id,
						signUpUser.name,signUpUser.password)
			}
			else {
				logger.info("User(Name : {}) sign up failed,time : {}", signUpUser.name, currentTime)
				return ErrorInfo(UserInterface.FAILED,"FAILED")
			}
		}
		else {
			logger.info("User(Name : {}) sign up failed,reason : {}", signUpUser.name, "UserName existed!")
			return ErrorInfo(UserInterface.FAILED,"UserName existed!")
		}
		
	}
	
	override fun login(loginUser : User) : ErrorInfo {
		var hql = "from User user where user.name=?"
		var returnResult = repository.query(hql, loginUser.name,returnType = User::class)
		if(returnResult != null && returnResult.size > 0) {
			var tempUser = returnResult[0] as User
			if(tempUser.name.equals(loginUser.name) && tempUser.password.equals(loginUser.password)) {
				logger.info("User(Name : {}) log in successful!", loginUser.name)
				return ErrorInfo(UserInterface.SUCCESS,"SUCCESS",tempUser.id,tempUser.name)
			}
		}
		logger.info("User(Name : {}) log in successful, reason : {}", loginUser.name, "UserName or Password isn't right!")
		return ErrorInfo(UserInterface.FAILED,"UserName or Password isn't right!")
	}
	
}