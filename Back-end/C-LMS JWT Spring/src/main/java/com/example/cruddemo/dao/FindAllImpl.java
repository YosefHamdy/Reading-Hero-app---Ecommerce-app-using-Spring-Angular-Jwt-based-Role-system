package com.example.cruddemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.cruddemo.entity.Book;
import com.example.cruddemo.entity.TransactionBookView;
import com.example.cruddemo.exceptions.BusinessException;

@CrossOrigin
@Repository
public class FindAllImpl implements FindAll {

	@Autowired
	private SessionFactory sessionFactory;

	// this Handles transaction management So we don't have to manually start and
	// commit transaction
	@SuppressWarnings("deprecation")
	@Override
	@Transactional
	public List<Book> findAll(Integer isbn) throws BusinessException {
		// TODO :get the current hibernate session
		Session currentSession = null;

		try {
			currentSession = sessionFactory.getSessionFactory().openSession();
			// create a query
			@SuppressWarnings("unchecked")
			Query<Book> theQuery = currentSession.getNamedQuery("listBook").setParameter("isbn",
					isbn == null ? -1 : isbn);
			// execute the query
			List<Book> books = theQuery.getResultList();
			// return the results
			return books;

		} catch (BusinessException e) {
			throw new BusinessException("Session Unresolved error");
		} finally {
			currentSession.close();
		}
	}
	@Override
	@SuppressWarnings("deprecation")
	@Transactional
	public List<TransactionBookView> findAllIssued(Integer customerId) {
		Session currentSession = sessionFactory.getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		Query<TransactionBookView> theQuery = currentSession.getNamedQuery("joinView");
		List<TransactionBookView> books = theQuery.getResultList();
		return books;
}
}
//public List<Book> findAll(Integer isbn) {
//	
//	Session currentSession = sessionFactory.getSessionFactory().openSession();
//	List<Book> books = null;
//	try{
//		// get the current hibernate session
//		if (currentSession != null && !currentSession.isOpen()) {
//			currentSession = sessionFactory.getSessionFactory().openSession();}
//		// create a query
//	Query<Book> theQuery = currentSession.getNamedQuery("listBook")
//									.setParameter("isbn", isbn == null ? -1 : isbn);
//	// execute the query
//	books = theQuery.getResultList();
//	// return the results
//	}catch (Exception e) {
//		e.printStackTrace();
//	}
//	return books;
//}