package com.firstapp.data

import com.firstapp.dataaccess.Repository

import org.hibernate.Transaction

import org.springframework.beans.factory.annotation.Autowired

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
	
}