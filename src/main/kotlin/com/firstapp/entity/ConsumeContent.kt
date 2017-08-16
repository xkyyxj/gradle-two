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
@Table(name="consume_content")
class ConsumeContent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var content_id : Long = 0
	
	lateinit var content : String;
	
	lateinit var consume_time : Date
	
	var consume_num : Float = 0f
	
	var consume_type_id : Long = 0
}