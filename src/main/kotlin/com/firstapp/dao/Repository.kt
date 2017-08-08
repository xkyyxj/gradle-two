package com.firstapp.dao

import kotlin.reflect.KClass

interface Repository {
	
	companion object OPERATION_STATUS{
		const val SUCCESS = 0
		const val FAILED = 1
	}
	
	fun insert(obj : Any) : RepositoryStatus
	
	fun delete(obj : Any) : RepositoryStatus
	
	fun update(obj : Any) : RepositoryStatus
	
	fun query(obj : Any) : Int
	
	fun query(sql : String, vararg parameters : Any, returnType : KClass<*>) : List<Any?>?
	
	fun query(hql : String, parameter : Map<String,Any>, returnType : KClass<*>) : List<*>?
}