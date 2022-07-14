package com.demo.test.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.mapping.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.test.entity.User;
import com.demo.test.entity.datatable.OrderingCriteria;
@Repository
@Transactional
public class UserDaoImpl implements UserDao{
	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * public Session getSession() { Session session = null; try {
	 * System.out.println("Current session"); session =
	 * this.sessionFactory.getCurrentSession(); } catch (Exception e) {
	 * System.out.println("New session"); System.out.println(e.getMessage());
	 * session = this.sessionFactory.openSession(); }
	 * 
	 * return session; }
	 */
		
	@Override
	public void save(User user) {
		sessionFactory.getCurrentSession().save(user);
	}
	
	@Override
	public List<User> getUsers() {
		
		TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
	
		return query.getResultList();
	}

	@Override
	public List<User> getUsersPage(int start, int length) {
	
		TypedQuery<User> query = sessionFactory.getCurrentSession().
				createQuery("from User")
				.setFirstResult(start)
				.setMaxResults(length);
		return query.getResultList();
	}

	@Override
	public List<User> getUserPageOrderByColumn(int start, int length, String colName, String dir) {
		TypedQuery<User> query = sessionFactory.getCurrentSession().
				createQuery("from User u ORDER BY u."+colName+" "+dir)
				.setFirstResult(start)
				.setMaxResults(length);
		return query.getResultList();
	}
	
	

}
