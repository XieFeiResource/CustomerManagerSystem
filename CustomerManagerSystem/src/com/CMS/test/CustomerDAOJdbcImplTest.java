package com.CMS.test;

import java.util.List;

import org.junit.Test;

import com.CMS.dao.CriteriaCustomer;
import com.CMS.dao.CustomerDAO;
import com.CMS.dao.impl.CustomerDAOJdbcImpl;
import com.CMS.domain.Customer;

public class CustomerDAOJdbcImplTest {

	private CustomerDAO customerDAO = 
			new CustomerDAOJdbcImpl();
	
	@Test
	public void testGetForListWithCriteriaCustomer(){
		CriteriaCustomer cc = new CriteriaCustomer("k", null, null);
		List<Customer> customers = customerDAO.getForListWithCriteriaCustomer(cc);
		System.out.println(customers); 
	}
	
	@Test
	public void testGetAll() {
		List<Customer> customers = customerDAO.getAll();
		System.out.println(customers); 
	}

	@Test
	public void testSave() {
		Customer customer = new Customer();
		customer.setAddress("ShangHai");
		customer.setName("Jerry");
		customer.setPhone("13720998654");
		
		customerDAO.save(customer);
	}

	@Test
	public void testGetInteger() {
		Customer cust = customerDAO.get(1);
		System.out.println(cust); 
	}

	@Test
	public void testDelete() {
		customerDAO.delete(1);
	}

	@Test
	public void testGetCountWithName() {
		long count = customerDAO.getCountWithName("Jerry");
		System.out.println(count); 
	}

}
