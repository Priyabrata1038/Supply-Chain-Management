package com.SpringBoot.Supply_Chain.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.SpringBoot.Supply_Chain.Entity.Orders;
import com.SpringBoot.Supply_Chain.Repository.OrderRepository;



@Repository
public class OrderDao {
	@Autowired
	private OrderRepository orderRepository;
	
	public Orders saveOrder(Orders order) {
		return orderRepository.save(order);
	}

	public Optional<Orders> findById(Integer id) {
		return orderRepository.findById(id);
	}

	public List<Orders> getAllOrder() {
		return orderRepository.findAll();
	}

	public void deleteOrder(Integer id) {
		orderRepository.deleteById(id);	
	}

	public Orders getOrderByTrackingNumber(Long trackingNumber) {
		return orderRepository.getOrderByTrackingNumber(trackingNumber);
	}
	
	public List<Orders> getOrderGreaterThan(Double price) {
		return orderRepository.getOrderGreaterThan(price);
	}
	public List<Orders> getOrderByStatus(String status){
		return orderRepository.getOrderByStatus(status);
	}
	public List<Orders> getOrderByCustomerId(Integer id) {
		return orderRepository.getOrderByCustomerId(id);
	}

	public Page<Orders> getOrderByPaginationAndSorting(int pageNumber, int pageSize, String field) {
	    return orderRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(field).descending()));
	}
}
