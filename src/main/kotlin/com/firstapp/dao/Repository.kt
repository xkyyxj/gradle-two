package com.firstapp.dao

import kotlin.reflect.KClass

interface Repository {
	
	companion object OPERATION_STATUS{
		const val SUCCESS = 0
		const val FAILED = 1
	}
	
	fun insert(obj : Any) : Int
	
	fun delete(obj : Any) : Int
	
	fun update(obj : Any) : Int
	
	fun query(obj : Any) : Int
	
	fun query(sql : String, vararg parameters : Any, returnType : KClass<*>) : List<Any?>?
}