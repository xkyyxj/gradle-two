package com.firstapp.data

import com.firstapp.dataaccess.Repository

import org.springframework.beans.factory.annotation.Autowired

class HibernateDataRepository : Repository{
	
	@Autowired
	lateinit var hibernateSessionFactory : HibernateSessionFactory
	
	override fun insert(obj : Any) : Any {
		var session = hibernateSessionFactory.getSession()
		var transaction  = session.beginTransaction()
		
		session.save(obj)
		
		transaction.commit()
		session.close()
		
		return obj
	}
	
	override fun delete(obj : Any) : Any  {
		var session = hibernateSessionFactory.getSession()
		var transaction  = session.beginTransaction()
		
		session.delete(obj)
		
		transaction.commit()
		session.close()
		return obj
	}
	
	override fun update(obj : Any) : Any  {
		var session = hibernateSessionFactory.getSession()
		var transaction  = session.beginTransaction()
		
		session.update(obj)
		
		transaction.commit()
		session.close()
		return obj
	}
	
	override fun query(obj : Any) : Any  {
		var session = hibernateSessionFactory.getSession()
		var transaction  = session.beginTransaction()
		
		//session.
		
		transaction.commit()
		session.close()
		return obj
	}
	
}