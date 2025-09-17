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
import com.SpringBoot.Supply_Chain.Entity.Orders;
import com.SpringBoot.Supply_Chain.Service.OrderService;



@RequestMapping("/api/order")
@RestController
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Orders>> saveOrder(@RequestBody Orders order){
		return orderService.saveOrder(order);
	}
	@GetMapping("/id/{id}")
	public ResponseEntity<ResponseStructure<Orders>> getOrderById(@PathVariable Integer id){
		return orderService.getOrderById(id);
	}
	@GetMapping
    public ResponseEntity<ResponseStructure<List<Orders>>> getAllOrder(){
       return orderService.getAllOrder();
       
    }
    
    @PutMapping
    public ResponseEntity<ResponseStructure<Orders>> updateOrder(@RequestBody Orders order) {
        return orderService.updateOrder(order);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<String>> deleteOrder(@PathVariable Integer id){
    	return orderService.deleteOrder(id);
    }
    
    @GetMapping("/trackingNumber/{trackingNumber}")
    public ResponseEntity<ResponseStructure<Orders>> updateProduct(@RequestBody Long trackingNumber) {
        return orderService.getOrderByTrackingNumber(trackingNumber);
    }
    @GetMapping("/greaterThan/{price}")
    public ResponseEntity<ResponseStructure<List<Orders>>> getOrderByGreaterThan(@PathVariable Double price){
    	return orderService.getOrderByGreaterThan(price);
    }
    @GetMapping("/status/{status}")
    public ResponseEntity<ResponseStructure<List<Orders>>> getOrderByStatus(@PathVariable String status){
    	return orderService.getOrderByStatus(status);
    }
    
    
    @GetMapping("/customerId/{id}")
	public ResponseEntity<ResponseStructure<List<Orders>>> getOrderByCustomerId(@PathVariable Integer id){
		return orderService.getOrderByCustomerId(id);
	}
    @GetMapping("/page/{pageNumber}/{pageSize}/sort/{field}")
    public ResponseEntity<ResponseStructure<Page<Orders>>> getOrderPaginationAndSort(
            @PathVariable int pageNumber, @PathVariable int pageSize, @PathVariable String field) {
        return orderService.getOrderByPaginationAndSorting(pageNumber, pageSize, field);
    }

}
