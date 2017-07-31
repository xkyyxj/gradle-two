package com.firstapp.hibernatedao

import com.firstapp.dao.Repository
import kotlin.reflect.KClass

import org.hibernate.Transaction

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
open class HibernateDataRepository : Repository{
	
	@Autowired
	lateinit var hibernateSessionFactory : HibernateSessionFactory
	
	override fun insert(obj : Any) : Int {
		var session = hibernateSessionFactory.getSession()
		var transaction : Transaction? = null
		try{
			transaction= session.beginTransaction()
			
			session.save(obj)
			
			transaction.commit()
			
		} catch (e : Exception){
			if(transaction != null) {
				transaction.rollback()
			}
			return Repository.FAILED
		} finally {
			session.close()
		}
		return Repository.SUCCESS
	}
	
	override fun delete(obj : Any) : Int  {
		var session = hibernateSessionFactory.getSession()
		var transaction : Transaction? = null
		try{
			transaction= session.beginTransaction()
			
			session.delete(obj)
			
			transaction.commit()
			
		} catch (e : Exception){
			if(transaction != null) {
				transaction.rollback()
			}
			return Repository.FAILED
		} finally {
			session.close()
		}
		return Repository.SUCCESS
	}
	
	override fun update(obj : Any) : Int  {
		var session = hibernateSessionFactory.getSession()
		var transaction : Transaction? = null
		try{
			transaction= session.beginTransaction()
			
			session.update(obj)
			
			transaction.commit()
			
		} catch (e : Exception){
			if(transaction != null) {
				transaction.rollback()
			}
			return Repository.FAILED
		} finally {
			session.close()
		}
		return Repository.SUCCESS
	}
	
	override fun query(obj : Any) : Int  {
		var session = hibernateSessionFactory.getSession()
		var transaction : Transaction? = null
		try{
			transaction= session.beginTransaction()
			
			//session.save(obj)
			
			transaction.commit()
			
		} catch (e : Exception){
			if(transaction != null) {
				transaction.rollback()
			}
			return Repository.FAILED
		} finally {
			session.close()
		}
		return Repository.SUCCESS
	}
	
	override fun query(sql : String, vararg parameters : Any, returnType : KClass<*>) : List<Any?>? {
		var returnResult : List<Any?>?
		var session = hibernateSessionFactory.getSession()
		var transaction : Transaction? = null
		try{
			transaction= session.beginTransaction()
			
			var tempQuery = session.createQuery(sql,returnType.java)
			
			for(index in parameters.indices){
				tempQuery.setParameter(index,parameters[index])
			}
			
			returnResult = tempQuery.list()
			
			transaction.commit()
			
		} catch (e : Exception){
			if(transaction != null) {
				transaction.rollback()
				throw e
			}
			return null
		} finally {
			session.close()
		}
		
		return returnResult
		
	}
	
}