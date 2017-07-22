package com.firstapp.dataaccess

interface Repository {
	
	companion object OPERATION_STATUS{
		const val SUCCESS = 1
		const val FAILED = 2
	}
	
	fun insert(obj : Any) : Int
	
	fun delete(obj : Any) : Int
	
	fun update(obj : Any) : Int
	
	fun query(obj : Any) : Int
}