package com.firstapp.entity

import java.util.Date

import javax.persistence.Table
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import org.springframework.stereotype.Component

@Component
@Entity
@Table(name="users")
class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var id : Long = 0
	
	lateinit var name : String
	
	lateinit var password : String
	
	lateinit var signuptime : Date
	
}