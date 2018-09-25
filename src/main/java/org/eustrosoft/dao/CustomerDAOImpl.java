package org.eustrosoft.dao;

import java.util.List;

import org.eustrosoft.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory; // org.hibernate.
	
	@Override
	// мы ее убрали на Service слой
	//@Transactional //org.springframework.transaction
	public List<Customer> getCustomers() {
		//Session - org.hibernate
		Session currentSession = sessionFactory.getCurrentSession();
		//Query - org.hibernate.query для 5.2 
		//order by lastName - это сортировка
		Query<Customer> query = currentSession.createQuery("from Customer order by lastName",
				Customer.class);
		List<Customer> customers = query.getResultList();
		return customers;
	}

	@Override
	public void save(Customer customer) {
		Session currentSession = sessionFactory.getCurrentSession();
		// hibernat проверит и сохранит
		currentSession.saveOrUpdate(customer);
	}
	
	@Override
	public Customer getCustomer(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Customer customer = currentSession.get(Customer.class, id);
		return customer;
	}
	
	@Override
	public void deleteCustomer(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete from Customer where id=:customerId");
		query.setParameter("customerId", id);
		query.executeUpdate();
	}
	
	@Override
    public List<Customer> searchCustomers(String theSearchName) {
        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        Query theQuery = null;
        if (theSearchName != null && theSearchName.trim().length() > 0) {
            // search for firstName or lastName ... case insensitive
            theQuery =currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
        }
        else {
            // theSearchName is empty ... so just get all customers
            theQuery =currentSession.createQuery("from Customer", Customer.class);            
        }
        List<Customer> customers = theQuery.getResultList();    
        return customers;
        
    }
}
