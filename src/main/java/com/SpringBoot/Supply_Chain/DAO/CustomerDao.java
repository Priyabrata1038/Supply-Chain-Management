package com.SpringBoot.Supply_Chain.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.SpringBoot.Supply_Chain.Entity.Customer;
import com.SpringBoot.Supply_Chain.Repository.CustomerRepository;

@Repository
public class CustomerDao {
	@Autowired
	private CustomerRepository customerRepository;

	public Optional<Customer> findById(Integer id) {
		return customerRepository.findById(id);
	}

	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	public List<Customer> getAllCustomer() {
		return customerRepository.findAll();
	}

	public void deleteCustomer(Integer id) {
		customerRepository.deleteById(id);
	}
	
	public Customer getCustomerByPhone(long phone) {
		return customerRepository.getCustomerByPhone(phone);
	}
	
	public Customer getCustomerByOrderId(Integer id) {
		return customerRepository.getCustomerByOrderId(id);
	}

//	public Page<Customer> getCustomerByPaginationAndSorting(int pageNumber,int pageSize,String field){
//		return   customerRepository.findAll(PageRequest.of(pageNumber, pageSize),Sort.by(field).descending());
//	}

	public Page<Customer> getCustomerByPaginationAndSorting(int pageNumber, int pageSize, String field) {
	    return customerRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(field).descending()));
	}

}
