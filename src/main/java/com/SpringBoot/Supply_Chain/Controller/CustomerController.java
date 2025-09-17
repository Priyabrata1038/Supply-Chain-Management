package com.SpringBoot.Supply_Chain.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.Supply_Chain.DTO.ResponseStructure;
import com.SpringBoot.Supply_Chain.Entity.Customer;
import com.SpringBoot.Supply_Chain.Service.CustomerService;


@RequestMapping("/api/customer")
@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Customer>> saveCustomer(@RequestBody Customer customer){
		return customerService.saveCustomer(customer);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Customer>> getCustomerById(@PathVariable Integer id){
		return customerService.getCustomerById(id);
	}
	
	
	@GetMapping
    public ResponseEntity<ResponseStructure<List<Customer>>> getAllCustomer(){
       return customerService.getAllCustomer();
       
    }
    
    @PutMapping
    public ResponseEntity<ResponseStructure<Customer>> updateCustomer(@RequestBody Customer customer) {
        return customerService.updateCustomer(customer);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<String>> deleteSupplier(@PathVariable Integer id){
    	return customerService.deleteCustomer(id);
    }
    
    @GetMapping("/phone/{phone}")
	public ResponseEntity<ResponseStructure<Customer>> getCustomerByPhone(@PathVariable Long phone){
		return customerService.getCustomerByPhone(phone);
	}
    
    @GetMapping("/orderId/{id}")
	public ResponseEntity<ResponseStructure<Customer>> getCustomerByOrderId(@PathVariable Integer id){
		return customerService.getCustomerByOrderId(id);
	}
	
    
//    @GetMapping("/page/{pageNumber}/{pageSize}/sort/{field}")
//    public ResponseEntity<ResponseStructure<Page<Customer>>> getCustomerPaginationAndSort(@PathVariable int pageNumber,@PathVariable int pageSize,@PathVariable String field) {
//    	return customerService.getCustomerByPaginationAndSorting(pageNumber, pageSize,field);
//    }
    @GetMapping("/page/{pageNumber}/{pageSize}/sort/{field}")
    public ResponseEntity<ResponseStructure<Page<Customer>>> getCustomerPaginationAndSort(
            @PathVariable int pageNumber, @PathVariable int pageSize, @PathVariable String field) {
        return customerService.getCustomerByPaginationAndSorting(pageNumber, pageSize, field);
    }
}
