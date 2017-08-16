package com.firstapp.serviceimpl

import java.util.Date
import java.security.SecureRandom
import java.security.MessageDigest
import java.math.BigInteger

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.apache.logging.log4j.core.impl.Log4jContextFactory

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
			
			//Generate Salt
			var secureRandom = SecureRandom()
			//var saltByte = arrayOfNulls<Byte?>(20)
			//val asc = Array(20, { i -> (i * i).toString() })
			//var saltByte : Array<Byte> = Array<Byte>(20,{ i -> (0) })
			//var saltByte : Array<Byte> = Array<Byte>(20,{0})
			var saltByte = ByteArray(32,{0})
			secureRandom.nextBytes(saltByte)
			var saltString = toHexString(saltByte)
		
			var messageDigest = MessageDigest.getInstance("SHA-256");
			var finalPassword = saltString + signUpUser.password;
			messageDigest.update(finalPassword.toByteArray())
			var passwordHash = toHexString(messageDigest.digest())
			
			var currentTime = Date()
			//记录该用户的"盐"
			signUpUser.salt = saltString
			//将明文密码替换为密文
			signUpUser.password = passwordHash
			
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
			
			//获取用户的"盐"并且计算用户的密码密文
			var password = tempUser.salt + loginUser.password
			var messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(password.toByteArray())
			var passwordHash = toHexString(messageDigest.digest())
			
			if(tempUser.password.equals(passwordHash)) {
				logger.info("User(Name : {}) log in successful!", loginUser.name)
				return ErrorInfo(UserInterface.SUCCESS,"SUCCESS",tempUser.id,tempUser.name)
			}
		}
		logger.info("User(Name : {}) log in failed, reason : {}", loginUser.name, "UserName or Password isn't right!")
		return ErrorInfo(UserInterface.FAILED,"UserName or Password isn't right!")
	}
	
	private fun toHexString(byteArray : ByteArray) : String {
		if(byteArray.size > 0){
			//获取一下绝对值，这样如果是负数的话就能够去掉生成字符串前面的"-"号（负号）
			var bigInteger = BigInteger(byteArray).abs()
			//16进制基数转换
			return bigInteger.toString(16)
		}
		else
			return ""
	}
	
}