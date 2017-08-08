package com.firstapp.hibernatedao

import kotlin.reflect.KClass

import org.hibernate.Transaction

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import com.firstapp.dao.Repository
import com.firstapp.dao.RepositoryStatus

@Component
open class HibernateDataRepository : Repository{
	
	@Autowired
	lateinit var hibernateSessionFactory : HibernateSessionFactory
	
	override fun insert(obj : Any) : RepositoryStatus {
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
			return RepositoryStatus(Repository.FAILED,null)
		} finally {
			session.close()
		}
		return RepositoryStatus(Repository.SUCCESS,obj)
	}
	
	override fun delete(obj : Any) : RepositoryStatus  {
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
			return RepositoryStatus(Repository.FAILED, null)
		} finally {
			session.close()
		}
		return RepositoryStatus(Repository.SUCCESS,obj)
	}
	
	override fun update(obj : Any) : RepositoryStatus  {
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
			return RepositoryStatus(Repository.FAILED, null)
		} finally {
			session.close()
		}
		return RepositoryStatus(Repository.SUCCESS,obj)
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
	
	/**
	 * 该方法的调用会导致Hibernate给出警告信息，应采用另个版本：@link query(hql : String, parameter : Map<String,String>, returnType : KClass<*>) : List<Any?>?
	 *
	 */
	@Deprecated("以JPA占位符等形式替代", ReplaceWith("query(hql : String, parameter : Map<String,String>, returnType : KClass<*>) : List<Any?>?"))
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
	
	override fun query(hql : String, parameter : Map<String,Any>, returnType : KClass<*>) : List<*>? {
		var returnResult : List<Any?>?
		var session = hibernateSessionFactory.getSession()
		var transaction : Transaction? = null
		try{
			transaction= session.beginTransaction()
			
			var tempQuery = session.createQuery(hql,returnType.java)
			
			for((key,value) in parameter){
				tempQuery.setParameter(key,value)
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