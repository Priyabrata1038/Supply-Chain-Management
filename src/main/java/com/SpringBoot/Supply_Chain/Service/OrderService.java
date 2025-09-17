package com.SpringBoot.Supply_Chain.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.SpringBoot.Supply_Chain.DAO.OrderDao;
import com.SpringBoot.Supply_Chain.DTO.ResponseStructure;
import com.SpringBoot.Supply_Chain.Entity.Orders;
import com.SpringBoot.Supply_Chain.Exception.IdNotFoundException;
import com.SpringBoot.Supply_Chain.Exception.NoRecordAvailableException;



@Service
public class OrderService {
	@Autowired
	private OrderDao orderDao;
	
	public ResponseEntity<ResponseStructure<Orders>> saveOrder(Orders order) {
		ResponseStructure<Orders> res = new ResponseStructure<Orders>();
		res.setStatusCode(HttpStatus.CREATED.value());
		res.setMessage("Order record is saved");
		res.setData(orderDao.saveOrder(order));
			
		return new ResponseEntity<ResponseStructure<Orders>>(res,HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Orders>> getOrderById(Integer id) {
		ResponseStructure<Orders> res = new ResponseStructure<>();
        Optional<Orders> opt = orderDao.findById(id);
        if(opt.isPresent()) {
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Order record retrieved");
            res.setData(opt.get());
            
            return new ResponseEntity<ResponseStructure<Orders>>(res, HttpStatus.OK);
        } else {
        	throw new IdNotFoundException("Order not found since id is invalid id");
        }
	}

	public ResponseEntity<ResponseStructure<List<Orders>>> getAllOrder() {
		ResponseStructure<List<Orders>> res = new ResponseStructure<>();
        List<Orders> orders= orderDao.getAllOrder();
        if (!orders.isEmpty()) {
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("All Order records retrieved");
            res.setData(orders);
            
            
            return new ResponseEntity<ResponseStructure<List<Orders>>>(res, HttpStatus.OK);
        } else {
        	throw new NoRecordAvailableException("Order not found since no order present in database");
        }
	}

	public ResponseEntity<ResponseStructure<Orders>> updateOrder(Orders order) {
		ResponseStructure<Orders> res = new ResponseStructure<>();
        Optional<Orders> opt = orderDao.findById(order.getId());
        if(opt.isPresent()) {
            orderDao.saveOrder(order);
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Successfully updated");
            res.setData(order);
           
            
            return new ResponseEntity<ResponseStructure<Orders>>(res, HttpStatus.OK);
        } else {
        	throw new IdNotFoundException("Order not updated since id is invalid");
        }
	}

	public ResponseEntity<ResponseStructure<String>> deleteOrder(Integer id) {
		ResponseStructure<String> res = new ResponseStructure<>();
        Optional<Orders> opt = orderDao.findById(id);
        if(opt.isPresent()) {
            orderDao.deleteOrder(id);
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
	
	public ResponseEntity<ResponseStructure<Orders>> getOrderByTrackingNumber(Long trackingNumber) {
		ResponseStructure<Orders> res = new ResponseStructure<>();
	    Orders order = orderDao.getOrderByTrackingNumber(trackingNumber);
	        if (order != null) {
	            res.setStatusCode(HttpStatus.OK.value());
	            res.setMessage("Supplier found for product id");
	            res.setData(order);
	            return new ResponseEntity<ResponseStructure<Orders>>(res, HttpStatus.OK);
	        } else {
	            throw new IdNotFoundException("No order found for order tracking Number: " + trackingNumber);
	        }
	}
	
	public ResponseEntity<ResponseStructure<List<Orders>>> getOrderByGreaterThan(@PathVariable Double price){
    	ResponseStructure<List<Orders>> res= new ResponseStructure<List<Orders>>();
    	List<Orders> orders = orderDao.getOrderGreaterThan(price);
    	if(orders.size()>0){
    		res.setStatusCode(HttpStatus.OK.value());
    		res.setMessage("Record Found");
    		res.setData(orders);
    		return new ResponseEntity<ResponseStructure<List<Orders>>>(res,HttpStatus.OK);
    	}else {
    		throw new NoRecordAvailableException("No record found");
    	}
    }
	public ResponseEntity<ResponseStructure<List<Orders>>> getOrderByStatus(@PathVariable String status){
    	ResponseStructure<List<Orders>> res= new ResponseStructure<List<Orders>>();
    	List<Orders> orders = orderDao.getOrderByStatus(status);
    	if(orders.size()>0){
    		res.setStatusCode(HttpStatus.OK.value());
    		res.setMessage("Record Found");
    		res.setData(orders);
    		return new ResponseEntity<ResponseStructure<List<Orders>>>(res,HttpStatus.OK);
    	}else {
    		throw new NoRecordAvailableException("No record found");
    	}
    }
	
	public ResponseEntity<ResponseStructure<List<Orders>>> getOrderByCustomerId(Integer id) {
		ResponseStructure<List<Orders>> res = new ResponseStructure<>();
	    List<Orders> order = orderDao.getOrderByCustomerId(id);
	        if (order != null) {
	            res.setStatusCode(HttpStatus.OK.value());
	            res.setMessage("Order found for customer id");
	            res.setData(order);
	            return new ResponseEntity<ResponseStructure<List<Orders>>>(res, HttpStatus.OK);
	        } else {
	            throw new IdNotFoundException("No order found for customer id: " + id);
	        }
	}
	
	public ResponseEntity<ResponseStructure<Page<Orders>>> getOrderByPaginationAndSorting(int pageNumber, int pageSize, String field) {
	    Page<Orders> orders = orderDao.getOrderByPaginationAndSorting(pageNumber, pageSize, field);
	    ResponseStructure<Page<Orders>> response = new ResponseStructure<>();
	    if (!orders.isEmpty()) {
	        response.setStatusCode(HttpStatus.OK.value());
	        response.setMessage("Order retrieved by Pagination");
	        response.setData(orders);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } else {
	        throw new NoRecordAvailableException("Order not found since no order present in database");
	    }
	}

}
