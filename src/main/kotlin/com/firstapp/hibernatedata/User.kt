package com.firstapp.data

import javax.persistence.Table
import org.springframework.stereotype.Component

@Table(name="Users")
class User {
	
	var id : Long = 0
	
	lateinit var name : String
	
	lateinit var password : String
	
}