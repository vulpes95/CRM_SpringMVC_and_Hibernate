package org.eustrosoft.service;

import java.util.List;

import org.eustrosoft.entity.Customer;

public interface CustomerService {

	List<Customer> getCustomers();

	void saveCustomer(Customer customer);
	
	Customer getCustomer(int id);

	void deleteCustomer(int id);

	List<Customer> searchCustomers(String theSearchName);
}
