package org.eustrosoft.dao;

import java.util.List;

import org.eustrosoft.entity.Customer;

public interface CustomerDAO {
	
	List<Customer> getCustomers();

	void save(Customer customer);

	Customer getCustomer(int id);

	void deleteCustomer(int id);

	List<Customer> searchCustomers(String theSearchName);
}
