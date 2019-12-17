package com.revature.repositories;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.revature.beans.User;
import com.revature.util.HibernateUtil;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@Override
	public int createUser(User u) {
		
		int userCreated = 0;
		try(Session s = HibernateUtil.getSession()) {
			
			Transaction tx = s.beginTransaction();
			userCreated = (int) s.save(u);
			tx.commit();
			return userCreated;
		}
	}
	
	@Override
	public List<User> getUsers() {
		
		try(Session s = HibernateUtil.getSession()) {
			
			String hql = "from User";
			Query<User> q = s.createQuery(hql, User.class);
			List<User> users = q.getResultList();
			return users;
		}
	}
	
	@Override
	public User getUserByUsernameAndPassword(String username, String password) {
		
		try(Session s = HibernateUtil.getSession()) {
			
			String hql = "from User where username = :usernameVar and password = :passwordVar";
			Query<User> q = s.createQuery(hql, User.class);
			q.setParameter("usernameVar", username);
			q.setParameter("passwordVar", password);
			List<User> u = q.getResultList();
			return u.get(0);
		}
	}

}
