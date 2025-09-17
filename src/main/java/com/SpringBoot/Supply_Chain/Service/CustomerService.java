package com.SpringBoot.Supply_Chain.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.SpringBoot.Supply_Chain.DAO.CustomerDao;
import com.SpringBoot.Supply_Chain.DTO.ResponseStructure;
import com.SpringBoot.Supply_Chain.Entity.Customer;
import com.SpringBoot.Supply_Chain.Exception.IdNotFoundException;
import com.SpringBoot.Supply_Chain.Exception.NoRecordAvailableException;

@Service
public class CustomerService {
	@Autowired
	private CustomerDao customerDao;
	
	
	public ResponseEntity<ResponseStructure<Customer>> saveCustomer(Customer customer) {
		ResponseStructure<Customer> res = new ResponseStructure<Customer>();
		res.setStatusCode(HttpStatus.CREATED.value());
		res.setMessage("Customer record is saved");
		res.setData(customerDao.saveCustomer(customer));
		
		return new ResponseEntity<ResponseStructure<Customer>>(res,HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Customer>> getCustomerById(Integer id) {
		ResponseStructure<Customer> res = new ResponseStructure<>();
        Optional<Customer> opt = customerDao.findById(id);
        if(opt.isPresent()) {
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("customer record retrieved");
            res.setData(opt.get());
            
            return new ResponseEntity<ResponseStructure<Customer>>(res, HttpStatus.OK);
        } else {
        	throw new IdNotFoundException("customer not found since id is invalid");
        }
	}

	public ResponseEntity<ResponseStructure<List<Customer>>> getAllCustomer() {
		ResponseStructure<List<Customer>> res = new ResponseStructure<>();
        List<Customer> customer = customerDao.getAllCustomer();
        if (!customer.isEmpty()) {
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("All Customer records retrieved");
            res.setData(customer);
            
            
            return new ResponseEntity<ResponseStructure<List<Customer>>>(res, HttpStatus.OK);
        } else {
        	throw new NoRecordAvailableException("Customer not found since no customer present in database");
        }

	}

	public ResponseEntity<ResponseStructure<Customer>> updateCustomer(Customer customer) {
		ResponseStructure<Customer> res = new ResponseStructure<>();
        Optional<Customer> opt = customerDao.findById(customer.getId());
        if(opt.isPresent()) {
            customerDao.saveCustomer(customer);
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Successfully updated");
            res.setData(customer);
           
            
            return new ResponseEntity<ResponseStructure<Customer>>(res, HttpStatus.OK);
        } else {
        	throw new IdNotFoundException("Customer not updated since id is invalid");
        }
	}

	public ResponseEntity<ResponseStructure<String>> deleteCustomer(Integer id) {
		ResponseStructure<String> res = new ResponseStructure<>();
        Optional<Customer> opt = customerDao.findById(id);
        if(opt.isPresent()) {
            customerDao.deleteCustomer(id);
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Successfully deleted");
            res.setData("Record with id: " + id + " has been deleted");
            
            return new ResponseEntity<ResponseStructure<String>>(res, HttpStatus.OK);
        } else {
            res.setStatusCode(HttpStatus.NOT_FOUND.value());
            res.setMessage("Failed to delete");
            res.setData("Record with id: " + id + " is not available");
            
            return new ResponseEntity<ResponseStructure<String>>(res, HttpStatus.NOT_FOUND);
        } 
	}

	public ResponseEntity<ResponseStructure<Customer>> getCustomerByPhone(Long phone) {
		ResponseStructure<Customer> res = new ResponseStructure<>();
        Customer customer= customerDao.getCustomerByPhone(phone);
        if (customer != null) {
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Customer found ");
            res.setData(customer);
            return new ResponseEntity<ResponseStructure<Customer>>(res, HttpStatus.OK);
        } else {
            throw new IdNotFoundException("No customer found");
        }
	}


	public ResponseEntity<ResponseStructure<Customer>> getCustomerByOrderId(Integer id) {
		ResponseStructure<Customer> res = new ResponseStructure<>();
        Customer customer= customerDao.getCustomerByOrderId(id);
        if (customer != null) {
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Customer found for product id");
            res.setData(customer);
            return new ResponseEntity<ResponseStructure<Customer>>(res, HttpStatus.OK);
        } else {
            throw new IdNotFoundException("No customer found");
        }
	}

//	public ResponseEntity<ResponseStructure<Page<Customer>>> getCustomerByPaginationAndSorting(int pageNumber,
//			int pageSize, String field) {
//		Page<Customer> customers= customerDao.getCustomerByPaginationAndSorting(pageNumber,pageSize,field);
//	     ResponseStructure<Page<Customer>> response = new ResponseStructure<>();
//	        if (!customers.isEmpty()) {
//	            response.setStatusCode(HttpStatus.OK.value());
//	            response.setMessage("Customer retrieved by Pageination ");
//	            response.setData(customers);
//	            return new ResponseEntity<ResponseStructure<Page<Customer>>>(response, HttpStatus.OK);
//	        } else {
//	        	throw new NoRecordAvailableException("cUSTOMER not found since no CUSTOMER present in database");
//
//	        }
//	}
	
	public ResponseEntity<ResponseStructure<Page<Customer>>> getCustomerByPaginationAndSorting(int pageNumber, int pageSize, String field) {
	    Page<Customer> customers = customerDao.getCustomerByPaginationAndSorting(pageNumber, pageSize, field);
	    ResponseStructure<Page<Customer>> response = new ResponseStructure<>();
	    if (!customers.isEmpty()) {
	        response.setStatusCode(HttpStatus.OK.value());
	        response.setMessage("Customer retrieved by Pagination");
	        response.setData(customers);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } else {
	        throw new NoRecordAvailableException("Customer not found since no customer present in database");
	    }
	}

}
