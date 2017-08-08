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
@Table(name="consume_type")
class ConsumeType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var consumetype_id : Long = 0;
	
	lateinit var name : String;
	
	var user_id : Long = 0
	
	lateinit var insert_time : Date
	
}