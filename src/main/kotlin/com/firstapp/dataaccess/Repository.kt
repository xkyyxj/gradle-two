package com.firstapp.dataaccess

interface Repository {
	
	fun insert(obj : Any) : Any
	
	fun delete(obj : Any) : Any
	
	fun update(obj : Any) : Any
	
	fun query(obj : Any) : Any
}