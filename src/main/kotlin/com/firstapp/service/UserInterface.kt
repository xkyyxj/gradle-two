package com.firstapp.service

import com.firstapp.entity.User

interface UserInterface {
	
	companion object {
		const val SUCCESS = 0
		const val FAILED = 1
	}
	
	fun signUp(signUpUser : User) : ErrorInfo
	
	fun login(loginUser : User) : ErrorInfo
	
}