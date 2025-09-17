package com.SpringBoot.Supply_Chain.Repository;
import org.springframework.data.domain.Pageable; 

import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.SpringBoot.Supply_Chain.Entity.Customer;




public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	@Query("select c from Customer c where c.phone=?1")
	Customer getCustomerByPhone(long phone);
	
    @Query("select o.customer from Orders o where o.id=?1")
	Customer getCustomerByOrderId(int id);
	Page<Customer> findAll(Pageable pageable);

}
