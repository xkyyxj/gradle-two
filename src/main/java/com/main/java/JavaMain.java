package com.main.java;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.firstapp.hibernatedao.*;

public class JavaMain {
	
	final static String what123 = null;
	
	public static void testArray(int[] what) {
		if(what.length > 0) {
			System.out.println("one");
		}
		
		Logger logger = LoggerFactory.getLogger(JavaMain.class);
		HibernateSessionFactory re = new HibernateSessionFactory();
		Session session = re.getSession();
		Query tempQuery = session.createQuery("");
		List<?> list = tempQuery.list();
	}
	
	public static void ha123(String[] ra) {
		if(ra.length > 0) {
			System.out.println("sdaasd");
		}
	}
	
	public static void walksjd() {
		String[] temp = new String[2];
		hao123(temp);
	}
	
	
	public static void hao123(String ...strings) {
		if(strings.length > 0) {
			System.out.println("asdkfasdf");
		}
	}
	
	public static void testClass(Object obj) {
		System.out.println(obj);
	}
	
	public static void main(String[] args) {
		/*HibernateSF sf = new HibernateSF();
		Session session = sf.getSession();
		Transaction tr = null;
		try {
			tr = session.beginTransaction();
			Query temp = session.createQuery("select user.name from User user where user.name=?");
			temp.setParameter(0, "123123");
			List<String> tempList = temp.list();
			tr.commit();
		}
		catch(Exception e){
			if(tr != null) {
				tr.rollback();
			}
		}
		finally {
			session.close();
		}*/
		Logger log = LoggerFactory.getLogger(JavaMain.class);
		System.out.println(log.getClass().getName());
		log.warn("123123213");
	}

}
